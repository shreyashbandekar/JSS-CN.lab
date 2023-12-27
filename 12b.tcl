set ns [new Simulator]
set tf [open lab6.tr w]
$ns trace-all $tf
set topo [new Topography]
$topo load_flatgrid 1500 1500
set nf [open lab6.nam w]
$ns namtrace-all-wireless $nf 1500 1500
$ns node-config -adhocRouting AODV \
        -llType LL \
        -macType Mac/802_11 \
        -ifqType Queue/DropTail/PriQueue \
        -ifqLen 1000 \
        -phyType Phy/WirelessPhy \
        -channelType Channel/WirelessChannel \
        -propType Propagation/TwoRayGround \
        -antType Antenna/OmniAntenna \
        -topoInstance $topo \
        -energyModel EnergyModel \
	-initialEnergy 100 \
	-rxPower 0.3 \
	-txPower 0.6 \
        -agentTrace ON \
        -routerTrace ON \
        -macTrace OFF   
Mac/802_11 set cdma_code_bw_start_   		0      ;
Mac/802_11 set cdma_code_bw_stop_   		63      ;
Mac/802_11 set cdma_code_init_start_   		64      ;
Mac/802_11 set cdma_code_init_stop_   		127      ;
Mac/802_11 set cdma_code_cqich_start_   	128     ;
Mac/802_11 set cdma_code_cqich_stop_   		195      ;
Mac/802_11 set cdma_code_handover_start_	196     ;
Mac/802_11 set cdma_code_handover_stop_ 	255      ;           
create-god 10
set n0 [$ns node]
set n1 [$ns node]
set n2 [$ns node]
set n3 [$ns node]
set n4 [$ns node]
set n5 [$ns node]
set n6 [$ns node]
set n7 [$ns node]
set n8 [$ns node]
set n9 [$ns node]
$n0 set X_ 1035.201 
$n0 set Y_ 444.699
$n0 set Z_ 0
$n1 set X_ 244.365
$n1 set Y_ 521.418
$n1 set Z_ 0
$n2 set X_ 18.1268
$n2 set Y_ 300.612
$n2 set Z_ 0
$n3 set X_ 723.89
$n3 set Y_ 343.533
$n3 set Z_ 0
$n4 set X_ 122.34
$n4 set Y_ 311.755
$n4 set Z_ 0
$n5 set X_ 373.498
$n5 set Y_ 472.206
$n5 set Z_ 0
$n6 set X_ 548.549 
$n6 set Y_ 361.062
$n6 set Z_ 0
$n7 set X_ 389.995
$n7 set Y_ 381.178
$n7 set Z_ 0
$n8 set X_ 494.798
$n8 set Y_ 477.771
$n8 set Z_ 0
$n9 set X_ 275.01
$n9 set Y_ 381.99
$n9 set Z_ 0
set udp0 [new Agent/UDP]
$ns attach-agent $n4 $udp0
set sink [new Agent/LossMonitor]
$ns attach-agent $n3 $sink
set cbr0 [new Application/Traffic/CBR]
$cbr0 set packetSize_ 1000
$cbr0 set interval_ 0.1
$cbr0 set maxpkts_ 1000
$cbr0 attach-agent $udp0
$ns connect $udp0 $sink
$ns at 1.00 "$cbr0 start"
source CDMAlink.tcl
proc finish { } {
    global ns nf tf
    $ns flush-trace
    exec nam lab6.nam &
    close $tf
    exit 0
    }
$ns at 250 "finish"
$ns run
#create another file :CDMAlink.tcl and execute ns 6.tcl -channel (or) ns 6.tcl
$ns at 0.1 "$n0 setdest 786 813 20"
$ns at 0.1 "$n1 setdest 895 890 20"
$ns at 0.1 "$n2 setdest 633 669 20"
$ns at 0.1 "$n3 setdest 1375 712 20"
$ns at 0.1 "$n4 setdest 773 680 20"
$ns at 0.1 "$n5 setdest 1024 841 20"
$ns at 0.1 "$n6 setdest 1199 730 20"
$ns at 0.1 "$n7 setdest 1041 750 20"
$ns at 0.1 "$n8 setdest 1146 846 20"
$ns at 0.1 "$n9 setdest 926 751 20"
$ns at 1.0 "$n4 add-mark m blue square"
$ns at 1.0 "$n3 add-mark m magenta square"
$ns at 1.0 "$n4 label SENDER"
$ns at 1.0 "$n3 label RECEIVER"
