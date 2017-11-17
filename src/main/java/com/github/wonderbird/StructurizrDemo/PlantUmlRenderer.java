package com.github.wonderbird.StructurizrDemo;

import com.github.wonderbird.StructurizrDemo.ArchitectureModel;
import com.github.wonderbird.StructurizrDemo.TwoStepPngRenderer;
import com.structurizr.io.WorkspaceWriter;
import com.structurizr.io.plantuml.PlantUMLWriter;

import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

class PlantUmlRenderer extends TwoStepPngRenderer
{
   PlantUmlRenderer(final ArchitectureModel aModel)
   {
      super(aModel);
   }

   @Override
   WorkspaceWriter getWorkspaceWriter()
   {
      return new PlantUMLWriter();
   }

   @Override
   List<String> getCommandToConvertIntermediateFileToPng(final Path aInputFilePath, final Path aOutputFilePath)
   {
      return Arrays.asList("plantuml", aInputFilePath.toString());
   }
}
