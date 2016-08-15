<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: anton
  Date: 19.07.16
  Time: 15:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
  <head>
    <script rel="<c:url value='/static/js/map/map.js' />"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css"/>
    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.4/angular.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.4/angular-resource.js"></script>
    <meta http-equiv='Content-Type' content='text/html; charset=utf-8'/>
    <link rel="stylesheet" href="<c:url value="/static/css/map.css"/> "/>
    <script src="<c:url value="../../static/js/controller/map_controller.js"/>"></script>
    <script src="<c:url value="../../static/js/map/map.js"/>"></script>
    <title>${Title}</title>
  </head>
  <body ng-controller="MapController as ctr">
  <div class="content">
    <div class="map" id="map"></div>
    <div class="panel mar-top-10">
      <form ng-submit="ctr.sendToServer()">
        <button type="submit" ng-click="ctr.sendToServer()" class="btn btn-primary btn-lg btn-block mar-top-10">Save a life</button>
      </form>

    </div>
  </div>
  <script type='text/javascript' src='http://www.bing.com/api/maps/mapcontrol?branch=release&callback=loadMapScenario' async defer></script>
  </body>
</html>
