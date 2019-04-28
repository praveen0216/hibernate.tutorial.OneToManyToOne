package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Course;
import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;

public class EagerLazyDemo {

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
			
			//Instructor details-->
			System.out.println("luv2code Instructor: "+tempInstructor);
			
			//option 1: getter method before session close in case of lazy loading to avoid lazyinitializationException
			System.out.println("luv2code Courses "+tempInstructor.getCourses());
			
			session.getTransaction().commit();
			
			session.close();
			System.out.println("session is now closed \n");
			
			//get course for the instructor
			System.out.println("luv2code Courses "+tempInstructor.getCourses());

		} catch (Exception e) {
			e.printStackTrace();
			

		}

		finally {
			session.close();
			System.out.println("Closing factory or connection");
			factory.close();
		}

	}

}
