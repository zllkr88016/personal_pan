package pan.bean;

public class UserFile {
	private int id;
	private String filename;
	private String file_path;
	private String file_md5;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public String getFile_path() {
		return file_path;
	}
	public void setFile_path(String file_path) {
		this.file_path = file_path;
	}
	public String getFile_md5() {
		return file_md5;
	}
	public void setFile_md5(String file_md5) {
		this.file_md5 = file_md5;
	}
	@Override
	public String toString() {
		return "UserFile [id=" + id + ", filename=" + filename + ", file_path=" + file_path + ", file_md5=" + file_md5
				+ "]";
	}
	
	
}
