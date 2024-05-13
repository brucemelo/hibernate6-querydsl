package com.github.brucemelo;

import com.github.brucemelo.infrastructure.AppHibernate;
import com.github.brucemelo.model.Student;
import com.querydsl.jpa.hibernate.HibernateQuery;

import static com.github.brucemelo.model.QStudent.student;


public class Main {

    public static void main(String[] args) {

        AppHibernate.getSessionFactory().inTransaction(session -> {
            var student = Student.newStudent("Bruce Melo");
            session.persist(student);
        });

        AppHibernate.getSessionFactory().inStatelessTransaction(session -> {
            var student = Student.newStudent("Catharine");
            session.insert(student);
        });

        AppHibernate.getSessionFactory().inSession(session -> {
            var query = new HibernateQuery<Student>(session);
            var students = query.from(student).fetch();
            students.forEach(student -> System.out.println(student.getName()));
        });

        AppHibernate.getSessionFactory().inStatelessSession(statelessSession -> {
            var query = new HibernateQuery<Student>(statelessSession);
            var student1 = query.from(student).where(student.name.eq("Bruce Melo"))
                    .fetchOne();
            System.out.println(student1.getName());
        });

        AppHibernate.getSessionFactory().inStatelessSession(statelessSession -> {
            var query = new HibernateQuery<Student>(statelessSession);
            var studentId = query.select(student.id).from(student)
                    .where(student.name.eq("Catharine"))
                    .fetchOne();
            System.out.println(studentId);
        });

    }

}