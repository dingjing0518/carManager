package com.jinshi.service;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

public interface BatchService {

    public int batchExport(String parkId, String roleName, HttpServletResponse response);

    public boolean deleteDir(String dir);

    public void batchImport(MultipartFile file,Integer parkId,Integer agentId);
}
