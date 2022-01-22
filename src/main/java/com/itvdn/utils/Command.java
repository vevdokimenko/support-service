package com.itvdn.utils;

import com.itvdn.entity.*;
import com.itvdn.helper.*;

import java.util.List;
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
        UserEntity user = new UserHelper().getUserById(Long.parseLong(id));
        Scanner scanner = new Scanner(System.in);
        boolean exitMenu = false;

        while (!exitMenu){
            System.out.println("Choose you want to edit (enter number):");
            System.out.println(new StringBuilder()
                    .append("1. First name\n")
                    .append("2. Last name\n")
                    .append("3. Email\n")
                    .append("4. Phone number\n")
                    .append("5. Postal code\n")
                    .append("6. user_name\n")
                    .append("7. password\n")
                    .append("8. role_id\n")
                    .append("9. Save changes\n")
                    .append("0. Cancel\n")
            );
            switch (scanner.nextLine()){
                case "1":
                    System.out.println("Enter first name:");
                    user.getProfile().setFirstName(scanner.nextLine());
                    break;
                case "2":
                    System.out.println("Enter last name:");
                    user.getProfile().setLastName(scanner.nextLine());
                    break;
                case "3":
                    System.out.println("Enter email:");
                    user.getProfile().setEmail(scanner.nextLine());
                    break;
                case "4":
                    System.out.println("Enter phone number:");
                    user.getProfile().setPhoneNumber(scanner.nextLine());
                    break;
                case "5":
                    System.out.println("Enter postal code:");
                    user.getProfile().setPostalCode(scanner.nextLine());
                    break;
                case "6":
                    System.out.println("Enter user_name:");
                    user.setUserName(scanner.nextLine());
                    break;
                case "7":
                    System.out.println("Enter password:");
                    user.setPassword(scanner.nextLine());
                    break;
                case "8":
                    UserRoleHelper helper = new UserRoleHelper();
                    for (UserRoleEntity item : helper.getUserRoleList()){
                        System.out.println("Enter role id: " + item.getId() + "-" + item.getRoleName());
                    }
                    user.setUserRole(helper.getUserRoleById(scanner.nextLong()));
                    break;
                case "9":
                    new UserHelper().updateUser(user);
                    break;
                case "0":
                    exitMenu = true;
                    System.out.println("Cancelling");
                    break;
                default:
                    exitMenu = true;
                    break;
            }
        }

    }

    public void deleteUserId(String id) {
        new UserHelper().deleteUserById(id);
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
