package uff.grupo_3.uff_chamada.response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ResponseService {
    private final ResponseRepository responseRepository;

    @Autowired
    public ResponseService(ResponseRepository responseRepository){
        this.responseRepository = responseRepository;
    }

    public Response getResponse(int id){
        return this.responseRepository.findById(id).orElseThrow(
            () -> new IllegalStateException("response de id " + id + " não existe"));
    }

    public void createResponse(Response response){
        this.responseRepository.save(response);
    }

    public void updateResponse(Response response){
        this.responseRepository.save(response);
    }

    public void deleteResponse(int id){
        this.responseRepository.deleteById(id);
    }
}
