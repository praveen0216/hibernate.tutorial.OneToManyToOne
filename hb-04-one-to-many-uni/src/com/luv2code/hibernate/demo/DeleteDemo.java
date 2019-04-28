package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;

public class DeleteDemo {

	public static void main(String[] args) {

		// create session factory
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class)
				.buildSessionFactory();

		// create session object
		Session session = factory.getCurrentSession();

		try {
			
			
			
			//start the transaction
			session.beginTransaction();
			
			//get instructor by primary key /id 
			int theId=1 ;
			Instructor tempInstructor = session.get(Instructor.class,theId);
			
			System.out.println("Found instructor: "+tempInstructor);
			
			if(tempInstructor!=null) {
				System.out.println("Deleting the Instructor "+tempInstructor);
				
				//Note : will also delete associated details object
				session.delete(tempInstructor);
				
			}
			
			//delete the instructor 
			
		
			session.getTransaction().commit();
			
		} catch (Exception e) {
			System.out.println("Error : " + e);

		}

		finally {
			System.out.println("Closing factory or connection");
			factory.close();
		}

	}

}
