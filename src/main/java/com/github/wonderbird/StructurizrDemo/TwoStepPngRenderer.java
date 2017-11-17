package com.github.wonderbird.StructurizrDemo;

import com.github.wonderbird.StructurizrDemo.ArchitectureModel;
import com.github.wonderbird.StructurizrDemo.PngRenderer;
import com.structurizr.Workspace;
import com.structurizr.io.WorkspaceWriter;
import com.structurizr.io.WorkspaceWriterException;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.TimeUnit;

abstract class TwoStepPngRenderer implements PngRenderer
{
   private final ArchitectureModel model;

   TwoStepPngRenderer(final ArchitectureModel aModel)
   {
      model = aModel;
   }

   abstract WorkspaceWriter getWorkspaceWriter();

   abstract List<String> getCommandToConvertIntermediateFileToPng(final Path aInputFilePath,
                                                                  final Path aOutputFilePath);

   @Override
   public void renderTo(final Path aFilePath) throws WorkspaceWriterException, IOException, InterruptedException
   {
      Path tempFilePath = replaceFileExtension(aFilePath);
      renderModelToIntermediateFile(tempFilePath);
      renderIntermediateFileToPng(tempFilePath, aFilePath);
   }

   private Path replaceFileExtension(final Path aFilePath)
   {
      String pathAsString = aFilePath.toString();

      String[] parts = pathAsString.split("\\.", -1);
      String replacedPath = parts[0] + ".txt";

      return Paths.get(replacedPath);
   }

   private void renderModelToIntermediateFile(Path aFilePath) throws IOException, WorkspaceWriterException
   {
      try(BufferedWriter fileWriter = Files.newBufferedWriter(aFilePath))
      {
         Workspace workspace = model.getWorkspace();
         WorkspaceWriter workspaceWriter = getWorkspaceWriter();
         workspaceWriter.write(workspace, fileWriter);
      }
   }

   private void renderIntermediateFileToPng(final Path aInputFilePath, final Path aOutputFilePath)
      throws IOException, InterruptedException
   {
      ProcessBuilder pb = new ProcessBuilder();
      List<String> command = getCommandToConvertIntermediateFileToPng(aInputFilePath, aOutputFilePath);
      pb.command(command);

      Process p = pb.start();
      p.waitFor(20, TimeUnit.SECONDS);
   }
}
