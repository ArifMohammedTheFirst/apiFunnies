    import org.json.JSONArray;
    import org.json.JSONObject;
    import java.net.URI;
    import java.net.http.HttpClient;
    import java.net.http.HttpRequest;
    import java.net.http.HttpResponse;
    import java.util.Scanner;

public class Main {

    public static void main(String[] args)
    {
        String APIkey = "a8a3287173d1f06657ef6f601e999209";

        String urlNowPlaying = "https://api.themoviedb.org/3/movie/now_playing?api_key=" + APIkey + "&language=en-US";
        String response = makeAPICall(urlNowPlaying);

        parseJSON(response);
    }

    public static String makeAPICall(String url)
    {
        try {
            URI myUri = URI.create(url); // creates a URI object from the url string
            HttpRequest request = HttpRequest.newBuilder().uri(myUri).build();

            HttpClient client = HttpClient.newHttpClient();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // UNCOMMENT TO PRINT OTHER PARTS OF THE RESPONSE
            //System.out.println(response.statusCode());
            //System.out.println(response.headers());
           return (response.body());

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public static void parseJSON(String jsonStr){
        JSONObject jsonObj = new JSONObject(jsonStr);
        JSONArray moviesList = jsonObj.getJSONArray("results");

        for(int i = 0; i < moviesList.length(); i++){
            JSONObject movieObj = moviesList.getJSONObject(i);
            String movieTitle = movieObj.getString("title");
            int movieId = movieObj.getInt("id");
            String posterPath = movieObj.getString("poster_path");
            boolean adult = movieObj.getBoolean("adult");

            System.out.println(movieId + " " + movieTitle + " " + posterPath + "    " + adult);
        }
    }
}
