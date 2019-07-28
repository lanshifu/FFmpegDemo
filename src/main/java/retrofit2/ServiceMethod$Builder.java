package retrofit2;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.net.URI;
import java.util.Map;
import java.util.Set;
import okhttp3.Headers;
import okhttp3.Headers$Builder;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.ParameterHandler.RawPart;
import retrofit2.ParameterHandler.RelativeUrl;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HEAD;
import retrofit2.http.HTTP;
import retrofit2.http.Header;
import retrofit2.http.HeaderMap;
import retrofit2.http.Multipart;
import retrofit2.http.OPTIONS;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.QueryName;
import retrofit2.http.Url;

final class ServiceMethod$Builder<T, R> {
    CallAdapter<T, R> callAdapter;
    MediaType contentType;
    boolean gotBody;
    boolean gotField;
    boolean gotPart;
    boolean gotPath;
    boolean gotQuery;
    boolean gotUrl;
    boolean hasBody;
    Headers headers;
    String httpMethod;
    boolean isFormEncoded;
    boolean isMultipart;
    final Method method;
    final Annotation[] methodAnnotations;
    final Annotation[][] parameterAnnotationsArray;
    ParameterHandler<?>[] parameterHandlers;
    final Type[] parameterTypes;
    String relativeUrl;
    Set<String> relativeUrlParamNames;
    Converter<ResponseBody, T> responseConverter;
    Type responseType;
    final Retrofit retrofit;

    ServiceMethod$Builder(Retrofit retrofit, Method method) {
        this.retrofit = retrofit;
        this.method = method;
        this.methodAnnotations = method.getAnnotations();
        this.parameterTypes = method.getGenericParameterTypes();
        this.parameterAnnotationsArray = method.getParameterAnnotations();
    }

    public ServiceMethod build() {
        this.callAdapter = createCallAdapter();
        this.responseType = this.callAdapter.responseType();
        if (this.responseType == Response.class || this.responseType == Response.class) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("'");
            stringBuilder.append(Utils.getRawType(this.responseType).getName());
            stringBuilder.append("' is not a valid response body type. Did you mean ResponseBody?");
            throw methodError(stringBuilder.toString(), new Object[0]);
        }
        int i;
        this.responseConverter = createResponseConverter();
        for (Annotation parseMethodAnnotation : this.methodAnnotations) {
            parseMethodAnnotation(parseMethodAnnotation);
        }
        if (this.httpMethod != null) {
            if (!this.hasBody) {
                if (this.isMultipart) {
                    throw methodError("Multipart can only be specified on HTTP methods with request body (e.g., @POST).", new Object[0]);
                } else if (this.isFormEncoded) {
                    throw methodError("FormUrlEncoded can only be specified on HTTP methods with request body (e.g., @POST).", new Object[0]);
                }
            }
            int length = this.parameterAnnotationsArray.length;
            this.parameterHandlers = new ParameterHandler[length];
            i = 0;
            while (i < length) {
                Type type = this.parameterTypes[i];
                if (Utils.hasUnresolvableType(type)) {
                    throw parameterError(i, "Parameter type must not include a type variable or wildcard: %s", type);
                }
                Annotation[] annotationArr = this.parameterAnnotationsArray[i];
                if (annotationArr != null) {
                    this.parameterHandlers[i] = parseParameter(i, type, annotationArr);
                    i++;
                } else {
                    throw parameterError(i, "No Retrofit annotation found.", new Object[0]);
                }
            }
            if (this.relativeUrl == null && !this.gotUrl) {
                throw methodError("Missing either @%s URL or @Url parameter.", this.httpMethod);
            } else if (!this.isFormEncoded && !this.isMultipart && !this.hasBody && this.gotBody) {
                throw methodError("Non-body HTTP method cannot contain @Body.", new Object[0]);
            } else if (this.isFormEncoded && !this.gotField) {
                throw methodError("Form-encoded method must contain at least one @Field.", new Object[0]);
            } else if (!this.isMultipart || this.gotPart) {
                return new ServiceMethod(this);
            } else {
                throw methodError("Multipart method must contain at least one @Part.", new Object[0]);
            }
        }
        throw methodError("HTTP method annotation is required (e.g., @GET, @POST, etc.).", new Object[0]);
    }

    private CallAdapter<T, R> createCallAdapter() {
        Type genericReturnType = this.method.getGenericReturnType();
        if (Utils.hasUnresolvableType(genericReturnType)) {
            throw methodError("Method return type must not include a type variable or wildcard: %s", genericReturnType);
        } else if (genericReturnType != Void.TYPE) {
            try {
                return this.retrofit.callAdapter(genericReturnType, this.method.getAnnotations());
            } catch (RuntimeException e) {
                throw methodError(e, "Unable to create call adapter for %s", genericReturnType);
            }
        } else {
            throw methodError("Service methods cannot return void.", new Object[0]);
        }
    }

    private void parseMethodAnnotation(Annotation annotation) {
        if (annotation instanceof DELETE) {
            parseHttpMethodAndPath("DELETE", ((DELETE) annotation).value(), false);
        } else if (annotation instanceof GET) {
            parseHttpMethodAndPath("GET", ((GET) annotation).value(), false);
        } else if (annotation instanceof HEAD) {
            parseHttpMethodAndPath("HEAD", ((HEAD) annotation).value(), false);
            if (!Void.class.equals(this.responseType)) {
                throw methodError("HEAD method must use Void as response type.", new Object[0]);
            }
        } else if (annotation instanceof PATCH) {
            parseHttpMethodAndPath("PATCH", ((PATCH) annotation).value(), true);
        } else if (annotation instanceof POST) {
            parseHttpMethodAndPath("POST", ((POST) annotation).value(), true);
        } else if (annotation instanceof PUT) {
            parseHttpMethodAndPath("PUT", ((PUT) annotation).value(), true);
        } else if (annotation instanceof OPTIONS) {
            parseHttpMethodAndPath("OPTIONS", ((OPTIONS) annotation).value(), false);
        } else if (annotation instanceof HTTP) {
            HTTP http = (HTTP) annotation;
            parseHttpMethodAndPath(http.method(), http.path(), http.hasBody());
        } else if (annotation instanceof retrofit2.http.Headers) {
            String[] value = ((retrofit2.http.Headers) annotation).value();
            if (value.length != 0) {
                this.headers = parseHeaders(value);
            } else {
                throw methodError("@Headers annotation is empty.", new Object[0]);
            }
        } else if (annotation instanceof Multipart) {
            if (this.isFormEncoded) {
                throw methodError("Only one encoding annotation is allowed.", new Object[0]);
            } else {
                this.isMultipart = true;
            }
        } else if (!(annotation instanceof FormUrlEncoded)) {
        } else {
            if (this.isMultipart) {
                throw methodError("Only one encoding annotation is allowed.", new Object[0]);
            } else {
                this.isFormEncoded = true;
            }
        }
    }

    private void parseHttpMethodAndPath(String str, String str2, boolean z) {
        if (this.httpMethod == null) {
            this.httpMethod = str;
            this.hasBody = z;
            if (!str2.isEmpty()) {
                int indexOf = str2.indexOf(63);
                if (indexOf != -1 && indexOf < str2.length() - 1) {
                    if (ServiceMethod.PARAM_URL_REGEX.matcher(str2.substring(indexOf + 1)).find()) {
                        throw methodError("URL query string \"%s\" must not have replace block. For dynamic query parameters use @Query.", str2.substring(indexOf + 1));
                    }
                }
                this.relativeUrl = str2;
                this.relativeUrlParamNames = ServiceMethod.parsePathParameters(str2);
                return;
            }
            return;
        }
        throw methodError("Only one HTTP method is allowed. Found: %s and %s.", this.httpMethod, str);
    }

    private Headers parseHeaders(String[] strArr) {
        Headers$Builder headers$Builder = new Headers$Builder();
        for (String str : strArr) {
            String str2;
            int indexOf = str2.indexOf(58);
            if (indexOf == -1 || indexOf == 0 || indexOf == str2.length() - 1) {
                throw methodError("@Headers value must be in the form \"Name: Value\". Found: \"%s\"", str2);
            }
            String substring = str2.substring(0, indexOf);
            str2 = str2.substring(indexOf + 1).trim();
            if ("Content-Type".equalsIgnoreCase(substring)) {
                MediaType parse = MediaType.parse(str2);
                if (parse != null) {
                    this.contentType = parse;
                } else {
                    throw methodError("Malformed content type: %s", str2);
                }
            }
            headers$Builder.add(substring, str2);
        }
        return headers$Builder.build();
    }

    private ParameterHandler<?> parseParameter(int i, Type type, Annotation[] annotationArr) {
        ParameterHandler<?> parameterHandler = null;
        for (Annotation parseParameterAnnotation : annotationArr) {
            ParameterHandler<?> parseParameterAnnotation2 = parseParameterAnnotation(i, type, annotationArr, parseParameterAnnotation);
            if (parseParameterAnnotation2 != null) {
                if (parameterHandler == null) {
                    parameterHandler = parseParameterAnnotation2;
                } else {
                    throw parameterError(i, "Multiple Retrofit annotations found, only one allowed.", new Object[0]);
                }
            }
        }
        if (parameterHandler != null) {
            return parameterHandler;
        }
        throw parameterError(i, "No Retrofit annotation found.", new Object[0]);
    }

    private ParameterHandler<?> parseParameterAnnotation(int i, Type type, Annotation[] annotationArr, Annotation annotation) {
        String value;
        boolean encoded;
        Class rawType;
        StringBuilder stringBuilder;
        Class rawType2;
        ParameterizedType parameterizedType;
        if (annotation instanceof Url) {
            if (this.gotUrl) {
                throw parameterError(i, "Multiple @Url method annotations found.", new Object[0]);
            } else if (this.gotPath) {
                throw parameterError(i, "@Path parameters may not be used with @Url.", new Object[0]);
            } else if (this.gotQuery) {
                throw parameterError(i, "A @Url parameter must not come after a @Query", new Object[0]);
            } else if (this.relativeUrl == null) {
                this.gotUrl = true;
                if (type == HttpUrl.class || type == String.class || type == URI.class || ((type instanceof Class) && "android.net.Uri".equals(((Class) type).getName()))) {
                    return new RelativeUrl();
                }
                throw parameterError(i, "@Url must be okhttp3.HttpUrl, String, java.net.URI, or android.net.Uri type.", new Object[0]);
            } else {
                throw parameterError(i, "@Url cannot be used with @%s URL", this.httpMethod);
            }
        } else if (annotation instanceof Path) {
            if (this.gotQuery) {
                throw parameterError(i, "A @Path parameter must not come after a @Query.", new Object[0]);
            } else if (this.gotUrl) {
                throw parameterError(i, "@Path parameters may not be used with @Url.", new Object[0]);
            } else if (this.relativeUrl != null) {
                this.gotPath = true;
                Path path = (Path) annotation;
                value = path.value();
                validatePathName(i, value);
                return new ParameterHandler.Path(value, this.retrofit.stringConverter(type, annotationArr), path.encoded());
            } else {
                throw parameterError(i, "@Path can only be used with relative url on @%s", this.httpMethod);
            }
        } else if (annotation instanceof Query) {
            Query query = (Query) annotation;
            value = query.value();
            encoded = query.encoded();
            rawType = Utils.getRawType(type);
            this.gotQuery = true;
            if (Iterable.class.isAssignableFrom(rawType)) {
                if (type instanceof ParameterizedType) {
                    return new ParameterHandler.Query(value, this.retrofit.stringConverter(Utils.getParameterUpperBound(0, (ParameterizedType) type), annotationArr), encoded).iterable();
                }
                stringBuilder = new StringBuilder();
                stringBuilder.append(rawType.getSimpleName());
                stringBuilder.append(" must include generic type (e.g., ");
                stringBuilder.append(rawType.getSimpleName());
                stringBuilder.append("<String>)");
                throw parameterError(i, stringBuilder.toString(), new Object[0]);
            } else if (!rawType.isArray()) {
                return new ParameterHandler.Query(value, this.retrofit.stringConverter(type, annotationArr), encoded);
            } else {
                return new ParameterHandler.Query(value, this.retrofit.stringConverter(ServiceMethod.boxIfPrimitive(rawType.getComponentType()), annotationArr), encoded).array();
            }
        } else if (annotation instanceof QueryName) {
            encoded = ((QueryName) annotation).encoded();
            rawType2 = Utils.getRawType(type);
            this.gotQuery = true;
            if (Iterable.class.isAssignableFrom(rawType2)) {
                if (type instanceof ParameterizedType) {
                    return new ParameterHandler.QueryName(this.retrofit.stringConverter(Utils.getParameterUpperBound(0, (ParameterizedType) type), annotationArr), encoded).iterable();
                }
                stringBuilder = new StringBuilder();
                stringBuilder.append(rawType2.getSimpleName());
                stringBuilder.append(" must include generic type (e.g., ");
                stringBuilder.append(rawType2.getSimpleName());
                stringBuilder.append("<String>)");
                throw parameterError(i, stringBuilder.toString(), new Object[0]);
            } else if (!rawType2.isArray()) {
                return new ParameterHandler.QueryName(this.retrofit.stringConverter(type, annotationArr), encoded);
            } else {
                return new ParameterHandler.QueryName(this.retrofit.stringConverter(ServiceMethod.boxIfPrimitive(rawType2.getComponentType()), annotationArr), encoded).array();
            }
        } else if (annotation instanceof QueryMap) {
            rawType2 = Utils.getRawType(type);
            if (Map.class.isAssignableFrom(rawType2)) {
                type = Utils.getSupertype(type, rawType2, Map.class);
                if (type instanceof ParameterizedType) {
                    parameterizedType = (ParameterizedType) type;
                    rawType2 = Utils.getParameterUpperBound(0, parameterizedType);
                    if (String.class == rawType2) {
                        return new ParameterHandler.QueryMap(this.retrofit.stringConverter(Utils.getParameterUpperBound(1, parameterizedType), annotationArr), ((QueryMap) annotation).encoded());
                    }
                    stringBuilder = new StringBuilder();
                    stringBuilder.append("@QueryMap keys must be of type String: ");
                    stringBuilder.append(rawType2);
                    throw parameterError(i, stringBuilder.toString(), new Object[0]);
                }
                throw parameterError(i, "Map must include generic types (e.g., Map<String, String>)", new Object[0]);
            }
            throw parameterError(i, "@QueryMap parameter type must be Map.", new Object[0]);
        } else if (annotation instanceof Header) {
            String value2 = ((Header) annotation).value();
            rawType2 = Utils.getRawType(type);
            if (Iterable.class.isAssignableFrom(rawType2)) {
                if (type instanceof ParameterizedType) {
                    return new ParameterHandler.Header(value2, this.retrofit.stringConverter(Utils.getParameterUpperBound(0, (ParameterizedType) type), annotationArr)).iterable();
                }
                stringBuilder = new StringBuilder();
                stringBuilder.append(rawType2.getSimpleName());
                stringBuilder.append(" must include generic type (e.g., ");
                stringBuilder.append(rawType2.getSimpleName());
                stringBuilder.append("<String>)");
                throw parameterError(i, stringBuilder.toString(), new Object[0]);
            } else if (!rawType2.isArray()) {
                return new ParameterHandler.Header(value2, this.retrofit.stringConverter(type, annotationArr));
            } else {
                return new ParameterHandler.Header(value2, this.retrofit.stringConverter(ServiceMethod.boxIfPrimitive(rawType2.getComponentType()), annotationArr)).array();
            }
        } else if (annotation instanceof HeaderMap) {
            Class rawType3 = Utils.getRawType(type);
            if (Map.class.isAssignableFrom(rawType3)) {
                type = Utils.getSupertype(type, rawType3, Map.class);
                if (type instanceof ParameterizedType) {
                    parameterizedType = (ParameterizedType) type;
                    rawType3 = Utils.getParameterUpperBound(0, parameterizedType);
                    if (String.class == rawType3) {
                        return new ParameterHandler.HeaderMap(this.retrofit.stringConverter(Utils.getParameterUpperBound(1, parameterizedType), annotationArr));
                    }
                    stringBuilder = new StringBuilder();
                    stringBuilder.append("@HeaderMap keys must be of type String: ");
                    stringBuilder.append(rawType3);
                    throw parameterError(i, stringBuilder.toString(), new Object[0]);
                }
                throw parameterError(i, "Map must include generic types (e.g., Map<String, String>)", new Object[0]);
            }
            throw parameterError(i, "@HeaderMap parameter type must be Map.", new Object[0]);
        } else if (annotation instanceof Field) {
            if (this.isFormEncoded) {
                Field field = (Field) annotation;
                value = field.value();
                encoded = field.encoded();
                this.gotField = true;
                Class rawType4 = Utils.getRawType(type);
                if (Iterable.class.isAssignableFrom(rawType4)) {
                    if (type instanceof ParameterizedType) {
                        return new ParameterHandler.Field(value, this.retrofit.stringConverter(Utils.getParameterUpperBound(0, (ParameterizedType) type), annotationArr), encoded).iterable();
                    }
                    stringBuilder = new StringBuilder();
                    stringBuilder.append(rawType4.getSimpleName());
                    stringBuilder.append(" must include generic type (e.g., ");
                    stringBuilder.append(rawType4.getSimpleName());
                    stringBuilder.append("<String>)");
                    throw parameterError(i, stringBuilder.toString(), new Object[0]);
                } else if (!rawType4.isArray()) {
                    return new ParameterHandler.Field(value, this.retrofit.stringConverter(type, annotationArr), encoded);
                } else {
                    return new ParameterHandler.Field(value, this.retrofit.stringConverter(ServiceMethod.boxIfPrimitive(rawType4.getComponentType()), annotationArr), encoded).array();
                }
            }
            throw parameterError(i, "@Field parameters can only be used with form encoding.", new Object[0]);
        } else if (annotation instanceof FieldMap) {
            if (this.isFormEncoded) {
                rawType2 = Utils.getRawType(type);
                if (Map.class.isAssignableFrom(rawType2)) {
                    type = Utils.getSupertype(type, rawType2, Map.class);
                    if (type instanceof ParameterizedType) {
                        parameterizedType = (ParameterizedType) type;
                        rawType2 = Utils.getParameterUpperBound(0, parameterizedType);
                        if (String.class == rawType2) {
                            Converter stringConverter = this.retrofit.stringConverter(Utils.getParameterUpperBound(1, parameterizedType), annotationArr);
                            this.gotField = true;
                            return new ParameterHandler.FieldMap(stringConverter, ((FieldMap) annotation).encoded());
                        }
                        stringBuilder = new StringBuilder();
                        stringBuilder.append("@FieldMap keys must be of type String: ");
                        stringBuilder.append(rawType2);
                        throw parameterError(i, stringBuilder.toString(), new Object[0]);
                    }
                    throw parameterError(i, "Map must include generic types (e.g., Map<String, String>)", new Object[0]);
                }
                throw parameterError(i, "@FieldMap parameter type must be Map.", new Object[0]);
            }
            throw parameterError(i, "@FieldMap parameters can only be used with form encoding.", new Object[0]);
        } else if (annotation instanceof Part) {
            if (this.isMultipart) {
                Part part = (Part) annotation;
                this.gotPart = true;
                value = part.value();
                rawType = Utils.getRawType(type);
                if (!value.isEmpty()) {
                    String[] strArr = new String[4];
                    strArr[0] = "Content-Disposition";
                    StringBuilder stringBuilder2 = new StringBuilder();
                    stringBuilder2.append("form-data; name=\"");
                    stringBuilder2.append(value);
                    stringBuilder2.append("\"");
                    strArr[1] = stringBuilder2.toString();
                    strArr[2] = "Content-Transfer-Encoding";
                    strArr[3] = part.encoding();
                    Headers of = Headers.of(strArr);
                    if (Iterable.class.isAssignableFrom(rawType)) {
                        if (type instanceof ParameterizedType) {
                            type = Utils.getParameterUpperBound(0, (ParameterizedType) type);
                            if (!MultipartBody.Part.class.isAssignableFrom(Utils.getRawType(type))) {
                                return new ParameterHandler.Part(of, this.retrofit.requestBodyConverter(type, annotationArr, this.methodAnnotations)).iterable();
                            }
                            throw parameterError(i, "@Part parameters using the MultipartBody.Part must not include a part name in the annotation.", new Object[0]);
                        }
                        stringBuilder = new StringBuilder();
                        stringBuilder.append(rawType.getSimpleName());
                        stringBuilder.append(" must include generic type (e.g., ");
                        stringBuilder.append(rawType.getSimpleName());
                        stringBuilder.append("<String>)");
                        throw parameterError(i, stringBuilder.toString(), new Object[0]);
                    } else if (rawType.isArray()) {
                        Class boxIfPrimitive = ServiceMethod.boxIfPrimitive(rawType.getComponentType());
                        if (!MultipartBody.Part.class.isAssignableFrom(boxIfPrimitive)) {
                            return new ParameterHandler.Part(of, this.retrofit.requestBodyConverter(boxIfPrimitive, annotationArr, this.methodAnnotations)).array();
                        }
                        throw parameterError(i, "@Part parameters using the MultipartBody.Part must not include a part name in the annotation.", new Object[0]);
                    } else if (!MultipartBody.Part.class.isAssignableFrom(rawType)) {
                        return new ParameterHandler.Part(of, this.retrofit.requestBodyConverter(type, annotationArr, this.methodAnnotations));
                    } else {
                        throw parameterError(i, "@Part parameters using the MultipartBody.Part must not include a part name in the annotation.", new Object[0]);
                    }
                } else if (Iterable.class.isAssignableFrom(rawType)) {
                    if (!(type instanceof ParameterizedType)) {
                        stringBuilder = new StringBuilder();
                        stringBuilder.append(rawType.getSimpleName());
                        stringBuilder.append(" must include generic type (e.g., ");
                        stringBuilder.append(rawType.getSimpleName());
                        stringBuilder.append("<String>)");
                        throw parameterError(i, stringBuilder.toString(), new Object[0]);
                    } else if (MultipartBody.Part.class.isAssignableFrom(Utils.getRawType(Utils.getParameterUpperBound(0, (ParameterizedType) type)))) {
                        return RawPart.INSTANCE.iterable();
                    } else {
                        throw parameterError(i, "@Part annotation must supply a name or use MultipartBody.Part parameter type.", new Object[0]);
                    }
                } else if (rawType.isArray()) {
                    if (MultipartBody.Part.class.isAssignableFrom(rawType.getComponentType())) {
                        return RawPart.INSTANCE.array();
                    }
                    throw parameterError(i, "@Part annotation must supply a name or use MultipartBody.Part parameter type.", new Object[0]);
                } else if (MultipartBody.Part.class.isAssignableFrom(rawType)) {
                    return RawPart.INSTANCE;
                } else {
                    throw parameterError(i, "@Part annotation must supply a name or use MultipartBody.Part parameter type.", new Object[0]);
                }
            }
            throw parameterError(i, "@Part parameters can only be used with multipart encoding.", new Object[0]);
        } else if (annotation instanceof PartMap) {
            if (this.isMultipart) {
                this.gotPart = true;
                rawType2 = Utils.getRawType(type);
                if (Map.class.isAssignableFrom(rawType2)) {
                    type = Utils.getSupertype(type, rawType2, Map.class);
                    if (type instanceof ParameterizedType) {
                        parameterizedType = (ParameterizedType) type;
                        rawType2 = Utils.getParameterUpperBound(0, parameterizedType);
                        if (String.class == rawType2) {
                            type = Utils.getParameterUpperBound(1, parameterizedType);
                            if (!MultipartBody.Part.class.isAssignableFrom(Utils.getRawType(type))) {
                                return new ParameterHandler.PartMap(this.retrofit.requestBodyConverter(type, annotationArr, this.methodAnnotations), ((PartMap) annotation).encoding());
                            }
                            throw parameterError(i, "@PartMap values cannot be MultipartBody.Part. Use @Part List<Part> or a different value type instead.", new Object[0]);
                        }
                        stringBuilder = new StringBuilder();
                        stringBuilder.append("@PartMap keys must be of type String: ");
                        stringBuilder.append(rawType2);
                        throw parameterError(i, stringBuilder.toString(), new Object[0]);
                    }
                    throw parameterError(i, "Map must include generic types (e.g., Map<String, String>)", new Object[0]);
                }
                throw parameterError(i, "@PartMap parameter type must be Map.", new Object[0]);
            }
            throw parameterError(i, "@PartMap parameters can only be used with multipart encoding.", new Object[0]);
        } else if (!(annotation instanceof Body)) {
            return null;
        } else {
            if (this.isFormEncoded || this.isMultipart) {
                throw parameterError(i, "@Body parameters cannot be used with form or multi-part encoding.", new Object[0]);
            } else if (this.gotBody) {
                throw parameterError(i, "Multiple @Body method annotations found.", new Object[0]);
            } else {
                try {
                    Converter requestBodyConverter = this.retrofit.requestBodyConverter(type, annotationArr, this.methodAnnotations);
                    this.gotBody = true;
                    return new ParameterHandler.Body(requestBodyConverter);
                } catch (RuntimeException e) {
                    throw parameterError(e, i, "Unable to create @Body converter for %s", type);
                }
            }
        }
    }

    private void validatePathName(int i, String str) {
        if (!ServiceMethod.PARAM_NAME_REGEX.matcher(str).matches()) {
            throw parameterError(i, "@Path parameter name must match %s. Found: %s", ServiceMethod.PARAM_URL_REGEX.pattern(), str);
        } else if (!this.relativeUrlParamNames.contains(str)) {
            throw parameterError(i, "URL \"%s\" does not contain \"{%s}\".", this.relativeUrl, str);
        }
    }

    private Converter<ResponseBody, T> createResponseConverter() {
        try {
            return this.retrofit.responseBodyConverter(this.responseType, this.method.getAnnotations());
        } catch (RuntimeException e) {
            throw methodError(e, "Unable to create converter for %s", this.responseType);
        }
    }

    private RuntimeException methodError(String str, Object... objArr) {
        return methodError(null, str, objArr);
    }

    private RuntimeException methodError(Throwable th, String str, Object... objArr) {
        str = String.format(str, objArr);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(str);
        stringBuilder.append("\n    for method ");
        stringBuilder.append(this.method.getDeclaringClass().getSimpleName());
        stringBuilder.append(".");
        stringBuilder.append(this.method.getName());
        return new IllegalArgumentException(stringBuilder.toString(), th);
    }

    private RuntimeException parameterError(Throwable th, int i, String str, Object... objArr) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(str);
        stringBuilder.append(" (parameter #");
        stringBuilder.append(i + 1);
        stringBuilder.append(")");
        return methodError(th, stringBuilder.toString(), objArr);
    }

    private RuntimeException parameterError(int i, String str, Object... objArr) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(str);
        stringBuilder.append(" (parameter #");
        stringBuilder.append(i + 1);
        stringBuilder.append(")");
        return methodError(stringBuilder.toString(), objArr);
    }
}
