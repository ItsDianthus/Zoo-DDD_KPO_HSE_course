package com.proj.KPO_DZ_2;

import com.proj.KPO_DZ_2.application.service.AnimalTransferService;
import com.proj.KPO_DZ_2.application.service.FeedingOrganizationService;
import com.proj.KPO_DZ_2.application.service.ZooStatisticsService;
import com.proj.KPO_DZ_2.domain.model.*;
import com.proj.KPO_DZ_2.domain.model.enums.EnclosureType;
import com.proj.KPO_DZ_2.domain.model.enums.FoodType;
import com.proj.KPO_DZ_2.domain.model.enums.HealthStatus;
import com.proj.KPO_DZ_2.domain.model.enums.Sex;
import com.proj.KPO_DZ_2.domain.repository.AnimalRepository;
import com.proj.KPO_DZ_2.domain.repository.EnclosureRepository;
import com.proj.KPO_DZ_2.domain.repository.FeedingScheduleRepository;
import com.proj.KPO_DZ_2.domain.valueobjects.AnimalName;
import com.proj.KPO_DZ_2.domain.valueobjects.EnclosureCapacity;
import com.proj.KPO_DZ_2.domain.valueobjects.FavoriteFood;
import com.proj.KPO_DZ_2.domain.valueobjects.FeedingTime;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Map;
import java.util.UUID;

@SpringBootApplication
public class KpoDz2Application {

	public static void main(String[] args) {
		SpringApplication.run(KpoDz2Application.class, args);
	}

	@Bean
	CommandLineRunner runner(
			AnimalRepository animalRepo,
			EnclosureRepository enclosureRepo,
			FeedingScheduleRepository scheduleRepo,
			AnimalTransferService transferService,
			FeedingOrganizationService feedingService,
			ZooStatisticsService statsService
	) {
		return args -> {
			Enclosure predator = new Enclosure(
					UUID.randomUUID(),
					EnclosureType.PREDATOR,
					300.0,
					EnclosureCapacity.of(3)
			);
			Enclosure birds = new Enclosure(
					UUID.randomUUID(),
					EnclosureType.BIRD,
					150.0,
					EnclosureCapacity.of(5)
			);
			Enclosure aquarium = new Enclosure(
					UUID.randomUUID(),
					EnclosureType.AQUARIUM,
					200.0,
					EnclosureCapacity.of(10)
			);
			enclosureRepo.save(predator);
			enclosureRepo.save(birds);
			enclosureRepo.save(aquarium);

			System.out.println(
					"Созданы вольеры:\n" +
							"  - Хищники (" + predator.getType() + "), ID=" + predator.getId() + ")\n" +
							"  - Птицы   (" + birds.getType()   + "), ID=" + birds.getId()   + ")\n" +
							"  - Аквариум(" + aquarium.getType()+ "), ID=" + aquarium.getId()+ ")"
			);

			Animal leo = new Animal(
					UUID.randomUUID(),
					"Lion",
					AnimalName.of("Лео"),
					LocalDate.of(2018, 5, 20),
					Sex.MALE,
					FavoriteFood.of("Мясо"),
					HealthStatus.HEALTHY,
					predator.getId()
			);
			Animal marta = new Animal(
					UUID.randomUUID(),
					"Monkey",
					AnimalName.of("Марта"),
					LocalDate.of(2020, 3, 10),
					Sex.FEMALE,
					FavoriteFood.of("Фрукты"),
					HealthStatus.HEALTHY,
					birds.getId()
			);
			Animal popug = new Animal(
					UUID.randomUUID(),
					"Parrot",
					AnimalName.of("Попуг"),
					LocalDate.of(2021, 7, 5),
					Sex.FEMALE,
					FavoriteFood.of("Семена"),
					HealthStatus.HEALTHY,
					birds.getId()
			);
			Animal nemo = new Animal(
					UUID.randomUUID(),
					"Fish",
					AnimalName.of("Немо"),
					LocalDate.of(2022, 11, 1),
					Sex.MALE,
					FavoriteFood.of("Корм для рыб"),
					HealthStatus.HEALTHY,
					aquarium.getId()
			);
			animalRepo.save(leo);
			animalRepo.save(marta);
			animalRepo.save(popug);
			animalRepo.save(nemo);

			System.out.println("Созданы животные: Лео, Марта, Попуг, Немо");

			transferService.transfer(marta.getId(), predator.getId());
			System.out.println("Животное Марта перемещено в вольер Хищники");

			aquarium.clean();
			enclosureRepo.save(aquarium);
			System.out.println("Проведена уборка вольера Аквариум");

			FeedingSchedule f1 = new FeedingSchedule(
					UUID.randomUUID(),
					leo.getId(),
					FeedingTime.of(LocalTime.now().plusSeconds(10)),
					FoodType.MEAT
			);
			FeedingSchedule f2 = new FeedingSchedule(
					UUID.randomUUID(),
					marta.getId(),
					FeedingTime.of(LocalTime.now().plusSeconds(20)),
					FoodType.FRUITS
			);
			FeedingSchedule f3 = new FeedingSchedule(
					UUID.randomUUID(),
					nemo.getId(),
					FeedingTime.of(LocalTime.now().plusSeconds(30)),
					FoodType.FISH
			);
			feedingService.scheduleFeeding(f1);
			feedingService.scheduleFeeding(f2);
			feedingService.scheduleFeeding(f3);
			System.out.println("Запланированы кормления для Лео, Марта и Немо");

			feedingService.completeFeeding(f1.getId());
			System.out.println("Кормление Лео выполнено");

			System.out.println("=== Статистика зоопарка ===");
			System.out.println("Всего животных: " + statsService.getTotalAnimals());
			System.out.println("Свободных вольеров: " + statsService.getFreeEnclosures().size());

			Map<UUID, Long> countMap = statsService.getCountPerEnclosure();
			enclosureRepo.findAll().forEach(enc -> {
				long count = countMap.getOrDefault(enc.getId(), 0L);
				String status = (enc.getCapacity().getValue() - enc.getCurrentAnimals() > 0)
						? "есть свободные места"
						: "полностью заполнен";
				System.out.printf(
						"В вольере «%s» (%s) — %d животных, %s%n",
						enc.getType(),
						enc.getId(),
						count,
						status
				);
			});
		};
	}


}
