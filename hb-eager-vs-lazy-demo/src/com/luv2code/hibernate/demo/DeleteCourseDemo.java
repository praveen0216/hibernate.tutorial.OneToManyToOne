package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Course;
import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;

public class DeleteCourseDemo {

	public static void main(String[] args) {

		// create session factory
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class).addAnnotatedClass(Course.class).buildSessionFactory();

		// create session object
		Session session = factory.getCurrentSession();

		try {

			// start the transaction
			session.beginTransaction();

			//get a course 
			int theId=10;
			Course tempCourse=session.get(Course.class,theId);
			
			if(tempCourse!=null) {
				System.out.println("deleting....");
				session.delete(tempCourse);
			}
			
			// delete a course
			
			
			
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
