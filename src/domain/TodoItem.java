package domain;

/**
 * every todo item in list
 * @author chenxs
 */
public class TodoItem {

	/**
	 * primary key of the item
	 */
	private int id;
	
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
	private long createTime;
	
	/**
	 * modify time about the todo item
	 */
	private long modifyTime;

	/**
	 * is finished or not
	 * 1 means finished,while 0 means not finished,still in progress
	 */
	private int isFinished;
	
	public int getIsFinished() {
		return isFinished;
	}

	public void setIsFinished(int isFinished) {
		this.isFinished = isFinished;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
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
