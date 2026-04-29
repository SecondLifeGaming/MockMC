/*
  This is the name of our MockMC artifact, it includes
  the API version of Minecraft we are targeting.
*/
rootProject.name = "MockMC-v${extensions.extraProperties.get("paper.api.version")}"

include(":extra:TestPlugin")
include(":metaminer")
