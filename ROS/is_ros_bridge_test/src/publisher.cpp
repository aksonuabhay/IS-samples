#include "ros/ros.h"
#include "std_msgs/String.h"
#include <sstream>

int main(int argc, char *argv[])
{
	ros::init(argc,argv,"publisher");
	ros::NodeHandle node;
	ros::Publisher pub=node.advertise<std_msgs::String>("output",10);
	ros::Rate loopRate(5);
	int msg_count=0;
	while(ros::ok())
	{
		std_msgs::String message;
		std::stringstream temp;
		temp << "Hello InterativeSpaces" << msg_count;
		message.data = temp.str();
		ROS_INFO("%s", message.data.c_str());
		pub.publish(message);
		ros::spinOnce();
		loopRate.sleep();     
		++msg_count;
	}
	return 0;
}