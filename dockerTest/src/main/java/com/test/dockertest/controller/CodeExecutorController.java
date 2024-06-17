package com.test.dockertest.controller;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.command.CreateContainerResponse;
import com.github.dockerjava.api.model.Bind;
import com.github.dockerjava.api.model.HostConfig;
import com.github.dockerjava.api.model.Volume;
import com.github.dockerjava.core.DockerClientBuilder;
import com.github.dockerjava.core.command.LogContainerResultCallback;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/execute")
public class CodeExecutorController {

    private final DockerClient dockerClient = DockerClientBuilder.getInstance().build();

    @PostMapping("/{language}")
    public String executeCode(@PathVariable String language, @RequestBody String code) throws IOException, InterruptedException {
        String fileName;
        String dockerImage;
        String command;

        switch (language.toLowerCase()) {
            case "java":
                fileName = "Main.java";
                dockerImage = "openjdk:17";
                command = "javac Main.java && java Main";
                break;
            case "cpp":
                fileName = "main.cpp";
                dockerImage = "gcc:latest";
                command = "g++ -o myapp main.cpp && ./myapp";
                break;
            case "python":
                fileName = "main.py";
                dockerImage = "python:3.x";
                command = "python main.py";
                break;
            default:
                return "Unsupported language";
        }

        // 파일 시스템에 코드 저장
        Path codeDir = Files.createTempDirectory("code");
        Path filePath = Paths.get(codeDir.toString(), fileName);
        try (FileWriter writer = new FileWriter(filePath.toFile())) {
            writer.write(code);
        }

        // Docker 컨테이너 실행
        Volume volume = new Volume("/usr/src/myapp");
        CreateContainerResponse container = dockerClient.createContainerCmd(dockerImage)
                .withCmd("sh", "-c", command)
                .withHostConfig(HostConfig.newHostConfig().withBinds(new Bind(codeDir.toString(), volume)))
                .withVolumes(volume)
                .exec();

        dockerClient.startContainerCmd(container.getId()).exec();

        String logs = dockerClient.logContainerCmd(container.getId())
                .withStdOut(true)
                .withStdErr(true)
                .exec(new LogContainerResultCallback())
                .awaitCompletion()
                .toString();

        dockerClient.removeContainerCmd(container.getId()).exec();
        Files.walk(codeDir)
                .map(Path::toFile)
                .forEach(File::delete);

        return logs;
    }
}



