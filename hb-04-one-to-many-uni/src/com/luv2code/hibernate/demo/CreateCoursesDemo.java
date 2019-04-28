package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Course;
import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;

public class CreateCoursesDemo {

	public static void main(String[] args) {

		// create session factory
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class).addAnnotatedClass(Course.class).buildSessionFactory();

		// create session object
		Session session = factory.getCurrentSession();

		try {

			// start the transaction
			session.beginTransaction();

			// get the instructor from db
			int theId = 1;

			Instructor tempInstructor = session.get(Instructor.class, theId);

			// create some courses
			Course tempCourse1 = new Course("Sherlock Holmes");
			Course tempCourse2 = new Course("3 mistakes");

			// add courses to instructor
			if (tempInstructor != null) {

				tempInstructor.add(tempCourse2);
			}

			// save the courses
			session.save(tempCourse2);

			session.getTransaction().commit();

		} catch (Exception e) {
			System.out.println("Error : " + e);

		}

		finally {
			session.close();
			System.out.println("Closing factory or connection");
			factory.close();
		}

	}

}
