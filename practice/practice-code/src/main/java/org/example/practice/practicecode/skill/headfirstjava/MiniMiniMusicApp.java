package org.example.practice.practicecode.skill.headfirstjava;

import javax.sound.midi.MidiEvent;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;
import javax.sound.midi.ShortMessage;
import javax.sound.midi.Track;

public class MiniMiniMusicApp {
    public static void main(String[] args) {
        MiniMiniMusicApp mini = new MiniMiniMusicApp();
        mini.play();
    }

    public void play() {
        try {
            // step1 节拍器-用于播放MIDI音sequence的硬件或软件设备
            Sequencer sequencer = MidiSystem.getSequencer();
            sequencer.open();

            // step2 节拍-设定节拍类型和速度（基于速度的定时类型，每小节4拍）
            Sequence sequence = new Sequence(Sequence.PPQ, 4);

            // step3 节拍的轨道
            Track track = sequence.createTrack();

            // step4
            // 设定轨道a 通过ShortMessage编辑消息，确保新消息的内容指定有效的MIDI消息
            ShortMessage aMsg = new ShortMessage();
            // command=控制命令 channel=不同频道、乐器 data1=音高（音调） data2=音量
            aMsg.setMessage(ShortMessage.NOTE_ON, 1, 44, 100);
            // MidiMessage=消息内容 tick=音长（播放时间 时长=1s/节拍数4=1/4s）
            MidiEvent noteOn = new MidiEvent(aMsg, 4);
            track.add(noteOn);

            // 设定轨道b
            ShortMessage bMsg = new ShortMessage();
            bMsg.setMessage(ShortMessage.NOTE_OFF, 1, 44, 100);
            MidiEvent noteOff = new MidiEvent(aMsg, 16);
            track.add(noteOff);

            // 载入乐谱并播放
            sequencer.setSequence(sequence);
            sequencer.start();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
