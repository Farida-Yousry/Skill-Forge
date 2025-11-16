import java.util.ArrayList;

public class Lesson {
	
		// Attributes
		private String title;
		private String lessonID;
		private String content;
		private ArrayList<String> resources; 
		
	  // constructor
		public Lesson(String lessonID,String title,String content,ArrayList<String> resources) {
		
			this.lessonID=lessonID;
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
		public String getLessonID() {
			return lessonID;
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
	}
