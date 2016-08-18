package com.epam.rudenkov;

import com.epam.rudenkov.domain.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import java.util.Arrays;
import java.util.List;

/**
 * Created by sergei-rudenkov on 17.8.16.
 */
@Configuration
@ComponentScan("com.epam.rudenkov.domain")
@EnableAutoConfiguration
@EnableJpaRepositories
public class Runner {

    EntityManagerFactory entityManagerFactory;
    static ApplicationContext ctx;


    public static void main(String[] args){

        ctx = SpringApplication.run(Runner.class, args);
        Runner r = new Runner();
        r.printAllUserNames();
    }

    private void printAllUserNames() {
        entityManagerFactory = (EntityManagerFactory) ctx.getBean("entityManagerFactory");
        EntityManager entitymanager = entityManagerFactory.createEntityManager();
        List<Member> memebers = entitymanager.createQuery("Select a from Member a", Member.class)
                .getResultList();
        memebers.stream().forEach(member -> System.out.println(member.getUsername()));
    }
}
