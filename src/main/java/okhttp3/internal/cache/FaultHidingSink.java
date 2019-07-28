package okhttp3.internal.cache;

import java.io.IOException;
import okio.c;
import okio.g;
import okio.q;

class FaultHidingSink extends g {
    private boolean hasErrors;

    /* Access modifiers changed, original: protected */
    public void onException(IOException iOException) {
    }

    FaultHidingSink(q qVar) {
        super(qVar);
    }

    public void write(c cVar, long j) throws IOException {
        if (this.hasErrors) {
            cVar.i(j);
            return;
        }
        try {
            super.write(cVar, j);
        } catch (IOException e) {
            this.hasErrors = true;
            onException(e);
        }
    }

    public void flush() throws IOException {
        if (!this.hasErrors) {
            try {
                super.flush();
            } catch (IOException e) {
                this.hasErrors = true;
                onException(e);
            }
        }
    }

    public void close() throws IOException {
        if (!this.hasErrors) {
            try {
                super.close();
            } catch (IOException e) {
                this.hasErrors = true;
                onException(e);
            }
        }
    }
}
