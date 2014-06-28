package constants;

/**
 * Created by chenxiaoshun on 14-6-28.
 */
public class TodoConstants {

    /**
     * DB NAME in mongodb
     */
    public static final String DB_NAME = "mongodb-todolist";

    /**
     * collection name in mongodb's db
     */
    public static final String COLLECTION_NAME = "todolist";

    /**
     * Finished flag for display
     */
    public static class FinishFlag {
        /**
         * the task has been finished,will not display in unfinished list
         */
        public static final int FINISHED = 1;

        /**
         * new added task ,remain in progress
         */
        public static final int NOT_FINISHED = 0;
    }


    /**
     * Delete constants for delete
     */
    public static class DeleteFlag {
        /**
         * task has been deleted,will never be available
         */
        public static final int DELETED = 1;

        /**
         * the task not deleted,remain in progress
         */
        public static final int NOT_DELETED = 0;
    }

}
