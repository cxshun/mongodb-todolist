package domain;

import java.lang.String;

/**
 * every todo item in list
 * @author chenxs
 */
public class TodoItem {

	/**
	 * primary key of the item
	 */
	private String id;
	
	/**
	 * title about the item
	 */
	private String title;
	
	/**
	 * content about the todo item,general within length 100,just keep it simple
	 */
	private String	content;
	
	/**
	 * comment about the item,general within length 500
	 */
	private String comment;
	
	/**
	 * create time
	 */
	private long createTime = System.currentTimeMillis();
	
	/**
	 * modify time about the todo item
	 */
	private long modifyTime = System.currentTimeMillis();

	/**
	 * is finished or not
	 * 1 means finished,while 0 means not finished,still in progress
	 */
	private int finished;

    /**
     * identify the whether the task has been deleted
     */
    private int deleted;

    /**
     * predict finish time about the task
     */
    private long predictFinishTime;

    public long getPredictFinishTime() {
        return predictFinishTime;
    }

    public void setPredictFinishTime(long predictFinishTime) {
        this.predictFinishTime = predictFinishTime;
    }

    public int getDeleted() {
        return deleted;
    }

    public void setDeleted(int deleted) {
        this.deleted = deleted;
    }

    public int getFinished() {
        return finished;
    }

    public void setFinished(int finished) {
        this.finished = finished;
    }

    public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}

	public long getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(long modifyTime) {
		this.modifyTime = modifyTime;
	}

}
