FROM peelsky/sbt:0.13.7
MAINTAINER Piotr Limanowski <plimanowski@gmail.com>

EXPOSE 9000
CMD sbt ~re-start
