package org.example.fraud_project.Web;
import org.example.fraud_project.dao.entity.Transaction;
import org.example.fraud_project.service.transactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;


import java.util.HashMap;
import java.util.Map;

@Controller
public class TransactionControler {

    private final String API_URL = "http://localhost:5001/predict"; // Flask API URL

    private final transactionService transactionService;
    private final RestTemplate restTemplate;

    @Autowired
    public TransactionControler(transactionService transactionService, RestTemplate restTemplate) {
        this.transactionService = transactionService;
        this.restTemplate = restTemplate;
    }

    // Show the transaction form to the user (GET method)
    @GetMapping("/transaction")
    public String showTransactionPage(Model model) {
        model.addAttribute("transaction", new Transaction()); // Add an empty transaction object to the model
        return "transaction"; // This will resolve to 'transaction.html' (or equivalent template)
    }

    // Handle the form submission and call the Flask API for fraud_img.jpeg prediction (POST method)
    @PostMapping("/predict")
    public String predictFraud(@ModelAttribute Transaction transaction, Model model) {
        try {
            // Prepare the transaction features for prediction as an array
            double[] features = new double[30];
            features[0] = transaction.getTime();
            features[1] = transaction.getV1();
            features[2] = transaction.getV2();
            features[3] = transaction.getV3();
            features[4] = transaction.getV4();
            features[5] = transaction.getV5();
            features[6] = transaction.getV6();
            features[7] = transaction.getV7();
            features[8] = transaction.getV8();
            features[9] = transaction.getV9();
            features[10] = transaction.getV10();
            features[11] = transaction.getV11();
            features[12] = transaction.getV12();
            features[13] = transaction.getV13();
            features[14] = transaction.getV14();
            features[15] = transaction.getV15();
            features[16] = transaction.getV16();
            features[17] = transaction.getV17();
            features[18] = transaction.getV18();
            features[19] = transaction.getV19();
            features[20] = transaction.getV20();
            features[21] = transaction.getV21();
            features[22] = transaction.getV22();
            features[23] = transaction.getV23();
            features[24] = transaction.getV24();
            features[25] = transaction.getV25();
            features[26] = transaction.getV26();
            features[27] = transaction.getV27();
            features[28] = transaction.getV28();
            features[29] = transaction.getAmount(); // Assuming amount is included in the 30 features

            // Build the request body as a JSON object with the features
            Map<String, Object> requestPayload = new HashMap<>();
            requestPayload.put("features", features);

            // Set the headers for the POST request (Content-Type: application/json)
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            // Create an HttpEntity with the request body and headers
            HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(requestPayload, headers);

            // Send a POST request to Flask API with the features as JSON
            ResponseEntity<String> response = restTemplate.exchange(API_URL, HttpMethod.POST, requestEntity, String.class);

            // Get the prediction result from the response
            String prediction = response.getBody();

            // Add the prediction to the model and render the result view
            model.addAttribute("prediction", prediction);  // Display prediction result

            return "transactionResult"; // Return the view for transaction result (transactionResult.html)
        } catch (Exception e) {
            model.addAttribute("error", "Error occurred: " + e.getMessage()); // Error handling
            return "transactionResult"; // Render error view (transactionResult.html)
        }
    }
}
