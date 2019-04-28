package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;

public class DeleteInstructorDetailDemo {

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
			
			//get the instructor detail object
			int theId=4;
			
			InstructorDetail tempInstructorDetail =session.get(InstructorDetail.class,theId );
			
			//print the instructor detail
			System.out.println("instructordetails : "+tempInstructorDetail);
			
			//print the associated instructor
			System.out.println("associatedInstructor : " + tempInstructorDetail.getInstructor() );
			
			//now delete the instructor detail
			System.out.println("Deleting tempInstructorDetail : "+tempInstructorDetail);
			
			//remove the association object reference  : break bi-directional link
			tempInstructorDetail.getInstructor().setInstructorDetail(null);
			
			session.delete(tempInstructorDetail);
			
			//commit transaction
			session.getTransaction().commit();
			
			System.out.println("Done!!");
			
			
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
