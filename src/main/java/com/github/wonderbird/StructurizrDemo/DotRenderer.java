package com.github.wonderbird.StructurizrDemo;

import com.github.wonderbird.StructurizrDemo.ArchitectureModel;
import com.github.wonderbird.StructurizrDemo.TwoStepPngRenderer;
import com.structurizr.io.WorkspaceWriter;
import com.structurizr.io.dot.DotWriter;

import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

class DotRenderer extends TwoStepPngRenderer
{
   DotRenderer(final ArchitectureModel aModel)
   {
      super(aModel);
   }

   @Override
   WorkspaceWriter getWorkspaceWriter()
   {
      return new DotWriter();
   }

   @Override
   List<String> getCommandToConvertIntermediateFileToPng(final Path aInputFilePath, final Path aOutputFilePath)
   {
      return Arrays.asList("dot", "-T", "png", "-o", aOutputFilePath.toString(), aInputFilePath.toString());
   }
}
