package com.savelife.mvc.apis.massaging.converter;

import org.springframework.stereotype.Component;

/**
 * Created by anton on 22.08.16.
 */
@Component
public class Converter {

  /*  *//*
    * route service
    * *//*
    @Autowired
    static RoutingService routingService;

    public static List<String> convert(List<UserEntity> entities){
        List<String> converted = new ArrayList<>();

        entities.forEach((k) -> {
            ServerMassage m = new ServerMassage();
            m.setTo(k.getToken());
            Data d = new Data();
            d.setMassageBody("Hi, would you like to rebuild your road path?");

            System.out.println(k.toString());

            routingService.getRoute(50.440772
                    , 30.404751
                    , 50.424121
                    , Double.parseDouble(k.getDestinationLongitude())
            ).forEach((v)-> System.out.println(v.toString()));


            *//*d.setPath(routingService.getRoute(Double.parseDouble(k.getCurrentLatitude())
                    , Double.parseDouble(k.getCurrentLongitude())
                    , Double.parseDouble(k.getDestinationLatitude())
                    , Double.parseDouble(k.getDestinationLongitude()) ));*//*
            m.setData(d);

            Gson gson = new Gson();

            converted.add(gson.toJson(m));
        });

        return converted;
    }*/
}
