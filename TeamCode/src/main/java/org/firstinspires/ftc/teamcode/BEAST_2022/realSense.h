#ifndef FTCROBOT_21_22_REALSENSE_H
#define FTCROBOT_21_22_REALSENSE_H

#include <librealsense2/rs.hpp> // Include Intel RealSense Cross Platform API
#include <JDK-DIR>
#include <jni.h>

class realSense {

// Create a Pipeline - this serves as a top-level API for streaming and processing frames
rs2::pipeline p;

// Configure and start the pipeline
p.start();

// Block program until frames arrive
rs2::frameset frames = p.wait_for_frames();

// Try to get a frame of a depth image
rs2::depth_frame depth = frames.get_depth_frame();

// Get the depth frame's dimensions
float width = depth.get_width();
float height = depth.get_height();

// Query the distance from the camera to the object in the center of the image
float dist_to_center = depth.get_distance(width / 2, height / 2);

// Print the distance
//std::cout << "The camera is facing an object " << dist_to_center << " meters away \r";

};



#endif //FTCROBOT_21_22_REALSENSE_H