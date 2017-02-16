package validation;

import java.io.IOException;
import java.util.Set;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

public class Valider {



	public static <T> boolean objet(T objet) {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();
		Set<ConstraintViolation<T>> violations = validator.validate(objet);

		if (violations.size() > 0) {
			String attribut = null;
			System.out.println("Impossible de valider les données :");
			for (ConstraintViolation<T> violation : violations) {
				if (attribut == violation.getRootBeanClass().getSimpleName());
				System.out.println("\t" + violation.getRootBeanClass().getSimpleName() + "." + violation.getPropertyPath() + " :");
				System.out.println("\t\t- " + violation.getMessage());
				attribut = violation.getRootBeanClass().getSimpleName();
			}
			return false;
		} else {
			return true;
		}
	}

	public static <T, V> boolean valeur(Class<T> classe, String propriete, V value) {
		Logger logger = Logger.getLogger(Valider.class.getName());
		Handler fh;
		
		try {
			fh = new FileHandler("TestLogging.log",1000,5);
			fh.setEncoding("UTF-8");
			logger.addHandler(fh);
			SimpleFormatter formatter = new SimpleFormatter();
			fh.setFormatter(formatter);
		} catch (SecurityException e) {
			logger.severe("impossible d'associer le FileHandler");
		} catch (IOException e) {
			logger.severe("impossible d'associer le FileHandler");
		}

		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();
		Set<ConstraintViolation<T>> violations = validator.validateValue(classe, propriete, value);

		if (violations.size() > 0) {
			int count = 0;
			logger.warning(("Impossible de valider les données :"));
			for (ConstraintViolation<T> violation : violations) {
				if (count == 0)
					logger.warning("\t" + violation.getRootBeanClass().getSimpleName() + "." + violation.getPropertyPath() + " :");
					logger.warning("\t\t- " + violation.getMessage());
				count++;
			}
			return false;
		} else {
			return true;
		}
	}

}
