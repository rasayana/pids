package pids.service;

import pids.core.Camera;
public interface CameraService {
   String getURL(Camera camera) throws Exception;
}