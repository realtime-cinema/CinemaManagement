package org.example.cinemamanagement.service.impl;

import io.socket.client.Socket;
import org.example.cinemamanagement.dto.PickSeatDTO;
import org.example.cinemamanagement.mapper.PickSeatMapper;
import org.example.cinemamanagement.model.Perform;
import org.example.cinemamanagement.model.PickSeat;
import org.example.cinemamanagement.model.User;
import org.example.cinemamanagement.payload.request.PickSeatRequest;
import org.example.cinemamanagement.repository.PerformRepository;
import org.example.cinemamanagement.repository.PickSeatRepository;
import org.example.cinemamanagement.repository.UserRepository;
import org.example.cinemamanagement.service.PickSeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class PickSeatServiceImpl implements PickSeatService {
    PickSeatRepository pickSeatRepository;
    PerformRepository performRepository;

    UserRepository userRepository;

    @Autowired
    public PickSeatServiceImpl(PickSeatRepository pickSeatRepository, PerformRepository performRepository, UserRepository userRepository) {
        this.pickSeatRepository = pickSeatRepository;
        this.performRepository = performRepository;
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public List<PickSeatDTO> addPickSeat(List<PickSeatRequest> pickSeatRequests, UUID performId) {
        Perform perform = performRepository.findById(performId).orElseThrow(
                () -> new RuntimeException("Perform not found")
        );

        User userTemp = (User) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();

        User user = userRepository.findById(userTemp.getId()).orElseThrow(
                () -> new RuntimeException("User not found"));

        pickSeatRequests.forEach(pickSeatRequest -> {
            if (pickSeatRepository.findByPerformIdAndXAndY(performId, pickSeatRequest.getX(), pickSeatRequest.getY()).isPresent()) {
                throw new RuntimeException("Seat already picked");
            }

            if (pickSeatRequest.getX() < 0 || pickSeatRequest.getX() > perform.getCinemaRoom().getCinemaLayout().getXIndex() ||
                    pickSeatRequest.getY() < 0 || pickSeatRequest.getY() > perform.getCinemaRoom().getCinemaLayout().getYIndex()) {
                throw new RuntimeException("Seat out of range");
            }

            PickSeat pickSeat = PickSeat.builder()
                    .perform(perform)
                    .user(user)
                    .x(pickSeatRequest.getX())
                    .y(pickSeatRequest.getY())
                    .build();
            pickSeatRepository.save(pickSeat);
        });

        return pickSeatRepository.findByUserId(user.getId()).stream()
                .map(PickSeatMapper::toDTO)
                .toList();
    }

    @Override
    public List<PickSeatDTO> getAllSeatsPickedOfPerform(UUID performID) {
        return pickSeatRepository.findByPerformId(performID).stream()
                .map(PickSeatMapper::toDTO).toList();
    }

    @Override
    public List<PickSeatDTO> getAllPickSeatsByUser() {
        User user = (User) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();

        return pickSeatRepository.findByUserId(user.getId()).stream()
                .map(PickSeatMapper::toDTO)
                .toList();
    }

    @Override
    public PickSeatDTO getPickSeatById() {
        return null;
    }


    @Override
    public PickSeatDTO deletePickSeat() {
        return null;
    }

    @Override
    public Socket getSocket() {
        Socket socket = null;
        return socket;
    }
}
