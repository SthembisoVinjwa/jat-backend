all: clean runserver

runserver: JobApplicationTrackerApplication.class
	java JobApplicationTrackerApplication

JobApplicationTrackerApplication.class: JobApplicationTrackerApplication.java
	@javac JobApplicationTrackerApplication.java

clean:
	@rm -f *.class
	@rm -f domain/*.class
	@rm -f exceptions/*.class
	@rm -f filters/*.class
	@rm -f repositories/*.class
	@rm -f resources/*.class
	@rm -f services/*.class
