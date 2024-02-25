

import static io.restassured.RestAssured.*;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;

public class GraphQLScript {
@Test
	public void graphql() {
		// TODO Auto-generated method stub
		
		//Query
		int characterId = 6108;
       String response = given().log().all().header("Content-Type","application/json ")
       .body("{\"query\":\"\\nquery($characterId: Int!, $episodeId :Int!,$locationId :Int!)   {\\n\\n  character(characterId: $characterId)\\n  {\\n    name\\n    gender\\n    status\\n    type\\n    id\\n  }\\n  location(locationId: $locationId)\\n  {\\n    name\\n    dimension\\n  }\\n  episode(episodeId :$episodeId)\\n  {\\n    name \\n    air_date\\n    episode\\n  }\\n  characters(filters: {name :\\\"Harshit\\\"})\\n  {\\n       info\\n        {\\n      count\\n        }\\n    result\\n    {\\n      name\\n      type\\n    }\\n  }\\n  episodes(filters: {episode: \\\"Netflix\\\"})\\n  {\\n   result\\n    {\\n      id\\n      name\\n      air_date\\n      episode\\n    }\\n  }\\n\\n}\\n\\n\",\"variables\":{\"characterId\":"+characterId+",\"locationId\":6935,\"episodeId\":5011}}")
       .when().post("https://rahulshettyacademy.com/gq/graphql").then().extract().response().asString();
       
       System.out.println(response);
       JsonPath js = new JsonPath(response);
       String charactername = js.getString("data.character.name");
       Assert.assertEquals(charactername,"Monu");
       
       //Mutation
       String newCharacter = "Monu";
       String mutationResponse = given().log().all().header("Content-Type","application/json ")
    	       .body("{\"query\":\"mutation($locationName:String!,$characterName:String!,$episodeName: String!)\\n{\\n  createLocation(location :{name:$locationName,type:\\\"Noida\\\",dimension:\\\"201310\\\"})\\n  {\\n      id\\n  }\\n    createCharacter(character :{name:$characterName,type:\\\"Avenger\\\",status:\\\"Alive\\\",species:\\\"Human\\\",gender:\\\"male\\\",image:\\\"png\\\",originId:6934,locationId:6934})\\n {\\n  id\\n }   \\n   createEpisode(episode : {name :$episodeName,air_date:\\\"1998\\\",episode:\\\"NetFlix\\\"})\\n  {\\n    id\\n  }\\n   deleteLocations(locationIds: [6921,6922])\\n  {\\n    locationsDeleted\\n  }\\n}\",\"variables\":{\"locationName\":\"India\",\"characterName\":\""+newCharacter+"\",\"episodeName\":\"Manifest\"}}")
    	       .when().post("https://rahulshettyacademy.com/gq/graphql").then().extract().response().asString();
    	       
    	       System.out.println(mutationResponse);
       
	}

}