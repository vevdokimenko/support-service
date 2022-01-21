package com.itvdn.utils;

import com.itvdn.entity.*;
import com.itvdn.helper.*;

import java.util.Scanner;

public class Command {
    public void fetchAllUsers() {
        UserHelper userHelper = new UserHelper();
        for (UserEntity item : userHelper.getUserList()) System.out.println(item);
    }

    public void fetchAllIncidents() {
        for (IncidentEntity item : new IncidentHelper().getIncidentList()) System.out.println(item);
    }

    public void fetchAllActiveIncidents() {
        for (IncidentEntity item : new IncidentHelper().getActiveIncidentList()) System.out.println(item);
    }

    public void fetchUserById(String id) {
        System.out.println(new UserHelper().getUserById(Long.parseLong(id)));
    }

    public void addUser() {
        Scanner scanner = new Scanner(System.in);
        ProfileEntity profile = new ProfileEntity();
        UserEntity user = new UserEntity();
        UserRoleHelper userRoleHelper = new UserRoleHelper();
        ProfileHelper profileHelper = new ProfileHelper();

        System.out.println("Enter first name:");
        profile.setFirstName(scanner.nextLine());

        System.out.println("Enter last name:");
        profile.setLastName(scanner.nextLine());

        System.out.println("Enter email:");
        profile.setEmail(scanner.nextLine());

        System.out.println("Enter phone number:");
        profile.setPhoneNumber(scanner.nextLine());

        System.out.println("Enter postal code:");
        profile.setPostalCode(scanner.nextLine());

        System.out.println("Enter user_name:");
        user.setUserName(scanner.nextLine());

        System.out.println("Enter password:");
        user.setPassword(scanner.nextLine());

        System.out.println("Enter role id " + userRoleHelper.getUserRoleList());
        long roleId = scanner.nextLong();
        user.setUserRole(userRoleHelper.getUserRoleById(roleId));

        long profileId = profileHelper.addProfile(profile);
        user.setProfile(profileHelper.getProfileById(profileId));
        new UserHelper().addUser(user);
    }

    public void updateUserId(String id) {
        //TODO
    }

    public void deleteUserId(String id) {
        //TODO
    }

    public void subscribeServiceId(UserEntity activeUser, String id) {
        try {
            ServiceEntity service = new ServiceHelper().getServiceById(Long.parseLong(id));
            new UserServiceHelper().addUserService(new UserServiceEntity(service, activeUser));
        } catch (Exception e) {
            System.err.println("No such service.");
        }
    }

    public void unsubscribeServiceId(UserEntity activeUser, String id) {
        try {
            ServiceEntity service = new ServiceHelper().getServiceById(Long.parseLong(id));
            new UserServiceHelper().deleteUserService(activeUser, service);
        } catch (Exception e) {
            System.err.println("No such service.");
        }
    }

    public void createIncident(UserEntity activeUser) {
        IncidentHelper helper = new IncidentHelper();

        System.out.println("Type service name:");
        String serviceName = new Scanner(System.in).nextLine();
        System.out.println("Describe your problem with " + serviceName + ":");
        String problemDescription = new Scanner(System.in).nextLine();

        IncidentEntity incident = new IncidentEntity(serviceName, true, problemDescription, activeUser);
        helper.addIncident(incident);

        System.out.println("Your ticket was added: " + incident);
    }

    public void closeIncident() {
        IncidentHelper helper = new IncidentHelper();

        System.out.println("Type id of incident you want to close:");
        for (IncidentEntity item : helper.getActiveIncidentList()) {
            System.out.println(item.getId() + " " + item.getServiceName() + " " + item.getProblemDescription());
        }

        long incidentId;
        try {
            incidentId = Long.parseLong(new Scanner(System.in).nextLine());
        } catch (Exception e) {
            System.err.println("Input should be a number.");
            return;
        }

        helper.closeIncident(incidentId);
    }
}
