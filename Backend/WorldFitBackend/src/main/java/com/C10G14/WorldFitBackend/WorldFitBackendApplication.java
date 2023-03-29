package com.C10G14.WorldFitBackend;

import com.C10G14.WorldFitBackend.entity.Exercise;
import com.C10G14.WorldFitBackend.entity.Routine;
import com.C10G14.WorldFitBackend.entity.Unit;
import com.C10G14.WorldFitBackend.enumeration.EUnit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WorldFitBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(WorldFitBackendApplication.class, args);

		//Exercise exercise = new Exercise(1L,"Correr","imagenURL", new Unit(EUnit.kms));
		//Routine routine = new Routine(1L,"rutina 1");
		//routine.addExercise(exercise,10,1,1);
		//System.out.println(routine);

	}

}
