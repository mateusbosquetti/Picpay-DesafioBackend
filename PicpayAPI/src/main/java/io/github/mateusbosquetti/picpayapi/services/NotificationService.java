package io.github.mateusbosquetti.picpayapi.services;

import io.github.mateusbosquetti.picpayapi.model.dto.request.NotificationRequestDTO;
import io.github.mateusbosquetti.picpayapi.model.entity.User;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@AllArgsConstructor
public class NotificationService {

    private RestTemplate restTemplate;

    public void sendNotification(User user, String message)  {
        String email = user.getEmail();

        NotificationRequestDTO notificationRequestDTO = new NotificationRequestDTO(email, message);

        /*
        ResponseEntity<String> notificationResponse = restTemplate.postForEntity("https://util.devi.tools/api/v1/notify", notificationRequestDTO, String.class);

        if (!(notificationResponse.getStatusCode() == HttpStatus.OK)) {
            System.out.println("Erro ao enviar");
            throw new Exception("Serviço de notificação está fora do ar");
        }

         */

    }

}
