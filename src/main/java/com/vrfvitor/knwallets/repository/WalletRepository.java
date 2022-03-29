package com.vrfvitor.knwallets.repository;

import com.vrfvitor.knwallets.model.*;
import org.springframework.data.jpa.repository.*;

import java.util.*;

public interface WalletRepository extends JpaRepository<Wallet, UUID> {
}
