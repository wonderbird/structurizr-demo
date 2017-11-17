package com.github.wonderbird.StructurizrDemo;

import com.structurizr.Workspace;
import com.structurizr.model.Container;
import com.structurizr.model.Model;
import com.structurizr.model.Person;
import com.structurizr.model.SoftwareSystem;
import com.structurizr.view.ContainerView;
import com.structurizr.view.ViewSet;

class ArchitectureModel
{
   private Workspace workspace;

   ArchitectureModel()
   {
      createDiagrams();
   }

   private void createDiagrams()
   {
      workspace = new Workspace("Getting Started", "This is a model of my software system");
      Model model = workspace.getModel();

      Person user = model.addPerson("The User", "A user of my software system.");
      SoftwareSystem softwareSystem = model.addSoftwareSystem("Software System", "My software system");

      user.uses(softwareSystem, "uses");

      ViewSet views = workspace.getViews();
      //      SystemContextView contextView =
      //         views.createSystemContextView(softwareSystem, "SystemContext", "An example of a System Context diagram.");
      //
      //      contextView.addAllSoftwareSystems();
      //      contextView.addAllPeople();

      Container webApp = softwareSystem.addContainer("WebApp", "Web Application", "Spring Boot");
      Container db = softwareSystem.addContainer("Database", "Database", "mySql");

      user.uses(webApp, "HTTPS");
      webApp.uses(db, "Reads from and writes to", "JDBC");

      ContainerView containerView =
         views.createContainerView(softwareSystem, "Containers", "The container diagram for My System.");
      containerView.add(user);
      containerView.addAllContainers();
      containerView.add(softwareSystem);
   }

   Workspace getWorkspace()
   {
      return workspace;
   }
}
