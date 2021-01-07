package ro.ase.proiect_draft.database.AsyncTaskRunner;

public interface Callback<R> {

    void runResultOnUiThread(R result);
}
