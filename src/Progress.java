public class Progress {
 private String courseId;
 private String lessonId;
	public Progress(String courseId,String lessonId) {
		this.courseId=courseId;
		this.lessonId=lessonId;
		
	}
	public String getCourseId() {
		return courseId;
	}
	public String getLessonId() {
		return lessonId;
	}

}