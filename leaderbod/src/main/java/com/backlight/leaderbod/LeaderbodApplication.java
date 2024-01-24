package com.backlight.leaderbod;

import com.backlight.leaderbod.model.Player;
import com.backlight.leaderbod.repositries.PlayerRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

@SpringBootApplication
public class LeaderbodApplication implements CommandLineRunner {

	@Autowired
	private PlayerRepo playerRepo;
	public static void main(String[] args) {
		SpringApplication.run(LeaderbodApplication.class, args);
	}
	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}
	public static Timestamp generateRandomTimestampForLastTwoWeeks() {
		// Get current time
		Instant currentInstant = Instant.now();

		// Get current time in LocalDateTime
		LocalDateTime currentDateTime = LocalDateTime.ofInstant(currentInstant, ZoneId.systemDefault());

		// Generate a random number of days between 0 and 13 (last two weeks)
		int randomDays = ThreadLocalRandom.current().nextInt(0, 14);

		// Subtract the random number of days from the current time
		LocalDateTime randomDateTime = currentDateTime.minusDays(randomDays);

		// Convert LocalDateTime to Instant and then to Timestamp
		Instant randomInstant = randomDateTime.atZone(ZoneId.systemDefault()).toInstant();
		return Timestamp.from(randomInstant);
	}
	@Override
	public void run(String... args) throws Exception{
		try{
			String [] countryCodes = {"AD", "AE", "AF", "AG", "AI", "AL", "AM", "AO", "AQ", "AR", "AS", "AT", "AU", "AW", "AX", "AZ",
					"BA", "BB", "BD", "BE", "BF", "BG", "BH", "BI", "BJ", "BL", "BM", "BN", "BO", "BQ", "BR", "BS",
					"BT", "BV", "BW", "BY", "BZ", "CA", "CC", "CD", "CF", "CG", "CH", "CI", "CK", "CL", "CM", "CN",
					"CO", "CR", "CU", "CV", "CW", "CX", "CY", "CZ", "DE", "DJ", "DK", "DM", "DO", "DZ", "EC", "EE",
					"EG", "EH", "ER", "ES", "ET", "FI", "FJ", "FK", "FM", "FO", "FR", "GA", "GB", "GD", "GE", "GF",
					"GG", "GH", "GI", "GL", "GM", "GN", "GP", "GQ", "GR", "GS", "GT", "GU", "GW", "GY", "HK", "HM",
					"HN", "HR", "HT", "HU", "ID", "IE", "IL", "IM", "IN", "IO", "IQ", "IR", "IS", "IT", "JE", "JM",
					"JO", "JP", "KE", "KG", "KH", "KI", "KM", "KN", "KP", "KR", "KW", "KY", "KZ", "LA", "LB", "LC",
					"LI", "LK", "LR", "LS", "LT", "LU", "LV", "LY", "MA", "MC", "MD", "ME", "MF", "MG", "MH", "MK",
					"ML", "MM", "MN", "MO", "MP", "MQ", "MR", "MS", "MT", "MU", "MV", "MW", "MX", "MY", "MZ", "NA",
					"NC", "NE", "NF", "NG", "NI", "NL", "NO", "NP", "NR", "NU", "NZ", "OM", "PA", "PE", "PF", "PG",
					"PH", "PK", "PL", "PM", "PN", "PR", "PS", "PT", "PW", "PY", "QA", "RE", "RO", "RS", "RU", "RW",
					"SA", "SB", "SC", "SD", "SE", "SG", "SH", "SI", "SJ", "SK", "SL", "SM", "SN", "SO", "SR", "SS",
					"ST", "SV", "SX", "SY", "SZ", "TC", "TD", "TF", "TG", "TH", "TJ", "TK", "TL", "TM", "TN", "TO",
					"TR", "TT", "TV", "TW", "TZ", "UA", "UG", "UM", "US", "UY", "UZ", "VA", "VC", "VE", "VG", "VI",
					"VN", "VU", "WF", "WS", "YE", "YT", "ZA", "ZM", "ZW"
			};
			String [] names= {"Emily Johnson",
					"Alexander Martinez",
					"Sophia Lee",
					"Noah Thompson",
					"Olivia Davis",
					"Ethan Anderson",
					"Ava Taylor",
					"Liam White",
					"Isabella Harris",
					"Aiden Smith",
					"Mia Miller",
					"Jackson Brown",
					"Harper Wilson",
					"Lucas Moore",
					"Emma Turner"};
			Timestamp [] timestamps= new Timestamp[10005];
			// score of every player is between 0 and 1000
			for(int i=1;i<10002;i++) {
				int countryIndex = ((i*i)%249*i)%249*7%249;
				int nameIndex = ((i*i)%15*i)%15*7%15;
				int score =((i*i)%1000*i)%1000*7%1000;;
				String name= names[nameIndex];
				String country = countryCodes[countryIndex];
				Player player = new Player();

				timestamps[i-1]=generateRandomTimestampForLastTwoWeeks();
				player.setTimeStamp(timestamps[i-1]);
				player.setScore(score);
				player.setCountry(country);
				player.setName(name);
				System.out.println(name);
				this.playerRepo.save(player);
			}
			for(int i=0;i<10000;i++){
				System.out.println(timestamps[i]);
			}

		}catch(Exception e){

		}
	}

}
