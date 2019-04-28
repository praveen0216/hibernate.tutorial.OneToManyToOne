package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Course;
import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;

public class CreateInstructorDemo {

	public static void main(String[] args) {

		// create session factory
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class)
				.addAnnotatedClass(Course.class)
				.buildSessionFactory();

		// create session object
		Session session = factory.getCurrentSession();

		try {
			// create a  object
			Instructor tempInstructor = new Instructor("Sushant","Rout","Lit@gmail.com");	
			
			InstructorDetail tempInstructorDetail =
					new InstructorDetail("http://www.Lit.com/youtube","luv 2 code");
			
			
			//associate the objects
			
			tempInstructor.setInstructorDetail(tempInstructorDetail);
			
			
			//start the transaction
			session.beginTransaction();
			
			
			//save the instructor : this will also save object in table 2 due to cascade
			
			System.out.println("Saving instructor: "+tempInstructor);
			session.save(tempInstructor);
			//commit transaction
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
