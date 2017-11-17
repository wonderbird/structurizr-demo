package com.github.wonderbird.StructurizrDemo;

import com.github.wonderbird.StructurizrDemo.ArchitectureModel;
import com.github.wonderbird.StructurizrDemo.DotRenderer;
import com.github.wonderbird.StructurizrDemo.PlantUmlRenderer;
import com.github.wonderbird.StructurizrDemo.PngRenderer;
import com.structurizr.io.WorkspaceWriterException;
import org.junit.Test;
import util.FileMonitor;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PngRendererTest
{
   private final Path targetDir = Paths.get("target");

   @Test
   public void renderDotDiagram() throws Exception
   {
      final ArchitectureModel model = new ArchitectureModel();
      final PngRenderer renderer = new DotRenderer(model);

      renderAndCheckOutputFile(renderer, targetDir.resolve("dot_diagram.png"));
   }

   @Test
   public void renderPlantUmlDiagram() throws InterruptedException, IOException, WorkspaceWriterException
   {
      final ArchitectureModel model = new ArchitectureModel();
      final PngRenderer renderer = new PlantUmlRenderer(model);

      renderAndCheckOutputFile(renderer, targetDir.resolve("plantuml_diagram.png"));
   }

   private void renderAndCheckOutputFile(final PngRenderer aRenderer, final Path aOutputFilePath) throws WorkspaceWriterException, IOException, InterruptedException
   {
      FileMonitor monitor = new FileMonitor(aOutputFilePath);

      aRenderer.renderTo(aOutputFilePath);

      monitor.assertModified();
   }
}
