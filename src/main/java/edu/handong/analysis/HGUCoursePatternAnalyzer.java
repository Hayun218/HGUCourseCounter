package edu.handong.analysis;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.TreeMap;

import edu.handong.analysis.datamodel.Course;
import edu.handong.analysis.datamodel.Student;
import edu.handong.analysis.utils.NotEnoughArgumentException;
import edu.handong.analysis.utils.Utils;

public class HGUCoursePatternAnalyzer {

	private HashMap<String, Student> students;

	/**
	 * This method runs our analysis logic to save the number courses taken by each
	 * student per semester in a result file. Run method must not be changed!!
	 * 
	 * @param args
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	public void run(String[] args) {

		try {
			// when there are not enough arguments from CLI, it throws the
			// NotEnoughArgmentException which must be defined by you.
			if (args.length < 2)
				throw new NotEnoughArgumentException();
		} catch (NotEnoughArgumentException e) {
			System.out.println(e.getMessage());
			System.exit(0);
		}

		String dataPath = args[0]; // csv file to be analyzed
		String resultPath = args[1]; // the file path where the results are saved.
		ArrayList<String> lines = Utils.getLines(dataPath, true);

		students = loadStudentCourseRecords(lines);

		// To sort HashMap entries by key values so that we can save the results by
		// student ids in ascending order.
		Map<String, Student> sortedStudents = new TreeMap<String, Student>(students);

		// Generate result lines to be saved.
		ArrayList<String> linesToBeSaved = countNumberOfCoursesTakenInEachSemester(sortedStudents);

		// Write a file (named like the value of resultPath) with linesTobeSaved.
		Utils.writeAFile(linesToBeSaved, resultPath);
	}

	/**
	 * This method create HashMap<String,Student> from the data csv file. Key is a
	 * student id and the corresponding object is an instance of Student. The
	 * Student instance have all the Course instances taken by the student.
	 * 
	 * @param lines
	 * @return
	 */
	private HashMap<String, Student> loadStudentCourseRecords(ArrayList<String> lines) {

		HashMap<String, Student> stu = new HashMap<String, Student>();
		ArrayList<String> keys = new ArrayList<String>();

		Course oneCourse = new Course(lines.get(0));
		Student student = new Student(oneCourse.getStudentId());
		student.addCourse(oneCourse);
		stu.put(oneCourse.getStudentId(), student);
		keys.add(oneCourse.getStudentId());

		int temp = 1;
		for (int i = 1; i < lines.size(); i++) {
			oneCourse = new Course(lines.get(i));
			keys.add(oneCourse.getStudentId());
			if (keys.get(i - 1).equals(keys.get(i)))
				student.addCourse(oneCourse);
			else {
				student = new Student(keys.get(i));
				student.addCourse(oneCourse);
			}
			System.out.println(keys.get(i) + oneCourse.getCourseName());
			System.out.println(student.getSemestersByYearAndSemester());
			stu.put(keys.get(i), student);
			if (temp == Integer.parseInt(keys.get(i))) {
				stu.put(keys.get(i), student);
			} else
				temp++;
		}
		return stu;

	}

	/**
	 * This method generate the number of courses taken by a student in each
	 * semester. The result file look like this: StudentID,
	 * TotalNumberOfSemestersRegistered, Semester, NumCoursesTakenInTheSemester
	 * 0001,14,1,9 0001,14,2,8 ....
	 * 
	 * 0001,14,1,9 => this means, 0001 student registered 14 semeters in total. In
	 * the first semeter (1), the student took 9 courses.
	 * 
	 * 
	 * @param sortedStudents
	 * @return
	 */
	private ArrayList<String> countNumberOfCoursesTakenInEachSemester(Map<String, Student> sortedStudents) {
		ArrayList<String> result = new ArrayList<String>();
		int stuID = 1;
		int NumCoursesTakenInTheSemester = 0;
		int TotalNumberOfSemestersRegistered = 0;
		
		result.add("StudentID, TotalNumberOfSemestersRegistered, Semester, NumCoursesTakenInTheSemester");

		for (int i = 0; i < sortedStudents.size(); i++) {
			Student stu = sortedStudents.get(Integer.toString(stuID));


		//	TotalNumberOfSemestersRegistered = stu.getSemestersByYearAndSemester(); // 여기서어떻게 값을 가져오지..?

			for (int j = 1; j <= TotalNumberOfSemestersRegistered; j++) {
				NumCoursesTakenInTheSemester = stu.getNumCourseInNthSementer(j);
				result.add(
						stuID + "," + TotalNumberOfSemestersRegistered + "," + j + "," + NumCoursesTakenInTheSemester);
			}
			
			stuID++;

		}
		return result;
	}
}
