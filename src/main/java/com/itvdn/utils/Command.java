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
        UserEntity user = new UserHelper().getUserById(Long.parseLong(id));
        Scanner scanner = new Scanner(System.in);
        boolean exitMenu = false;

        while (!exitMenu){
            System.out.println("Choose you want to edit (enter number):");
            System.out.println("""
                    1. First name
                    2. Last name
                    3. Email
                    4. Phone number
                    5. Postal code
                    6. user_name
                    7. password
                    8. role_id
                    9. Save changes
                    0. Cancel
                    """
            );
            switch (scanner.nextLine()) {
                case "1" -> {
                    System.out.println("Enter first name:");
                    user.getProfile().setFirstName(scanner.nextLine());
                }
                case "2" -> {
                    System.out.println("Enter last name:");
                    user.getProfile().setLastName(scanner.nextLine());
                }
                case "3" -> {
                    System.out.println("Enter email:");
                    user.getProfile().setEmail(scanner.nextLine());
                }
                case "4" -> {
                    System.out.println("Enter phone number:");
                    user.getProfile().setPhoneNumber(scanner.nextLine());
                }
                case "5" -> {
                    System.out.println("Enter postal code:");
                    user.getProfile().setPostalCode(scanner.nextLine());
                }
                case "6" -> {
                    System.out.println("Enter user_name:");
                    user.setUserName(scanner.nextLine());
                }
                case "7" -> {
                    System.out.println("Enter password:");
                    user.setPassword(scanner.nextLine());
                }
                case "8" -> {
                    UserRoleHelper helper = new UserRoleHelper();
                    for (UserRoleEntity item : helper.getUserRoleList()) {
                        System.out.println("Enter role id: " + item.getId() + "-" + item.getRoleName());
                    }
                    user.setUserRole(helper.getUserRoleById(scanner.nextLong()));
                }
                case "9" -> new UserHelper().updateUser(user);
                case "0" -> {
                    exitMenu = true;
                    System.out.println("Cancelling");
                }
                default -> exitMenu = true;
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
