package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import dto.Task;
import dto.User;

public class UserDao {
	EntityManagerFactory factory = Persistence.createEntityManagerFactory("dev");
	EntityManager manager = factory.createEntityManager();
	EntityTransaction transaction = manager.getTransaction();

	public void save(User user) {
		transaction.begin();
		manager.persist(user);
		transaction.commit();
	}

	public User fetchByEmail(String email) {
		List<User> list = manager.createQuery("select x from User x where email=?1").setParameter(1, email)
				.getResultList();
		if (list.isEmpty())
			return null;
		else
			return list.get(0);
	}

	public void save(Task task) {
		transaction.begin();
		manager.persist(task);
		transaction.commit();
	}

	public List<Task> fetchAllTasks() {
		return manager.createQuery("select x from Task x").getResultList();
	}
}
