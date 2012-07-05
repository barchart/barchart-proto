################################################################################
#
# This Make file is written to support HP-UX and Linux compilation
#
################################################################################
TARGET = opra_c_decoder

SRCS    = \
    fast_api.c \
    fast_wrapper.c \
    fast_decode.c \
    fast_process.c \
    fast_main.c

HDRS    = \
    common.h \
    types.h \
    opra_fast.h \
    fast_decode.h \
    fast_process.h \
    fast_wrapper.h \
    fast_api.h

$(TARGET): $(SRCS) $(HDRS)

#----------------------------------------------------
# for HP-UX compile use following

#aCC -g -DDEBUG -o $(TARGET) $(SRCS)

#----------------------------------------------------
# for Linux compile use following

	g++ -g -DDEBUG  -o $(TARGET) $(SRCS) 

#----------------------------------------------------

clean:
	rm -f *.o $(TARGET)
