package BackEnd;
import java.util.ArrayList;

public class Lesson {
	
		// Attributes
		private String title;
		private String lessonId;
		private String content;
		private ArrayList<String> resources; 
		
	  // constructor
		public Lesson(String lessonId,String title,String content,ArrayList<String> resources) {
		
			this.lessonId=lessonId;
			this.title=title;
			this.content=content;
			this.resources=resources;
   }
	// setters
		public void setContent(String content) {
			this.content=content;
		}
		public void  setTitle(String title) {
			this.title=title;
		}
		public void setResources(ArrayList<String> resources) {
			this.resources=resources;
		}
		
	// getters
		public String getLessonId() {
			return lessonId;
		}
		public String getTitle() {
			return title;
		}
		public String getContent() {
			return content;
		}
		public ArrayList<String> getResources() {
			return resources;
		}
		
		public String toString() {
			return lessonId+"-"+title;
		}
}