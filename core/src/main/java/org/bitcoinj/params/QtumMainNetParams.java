package org.bitcoinj.params;

import org.bitcoinj.core.*;
import org.bitcoinj.net.discovery.*;

import java.net.*;

import static com.google.common.base.Preconditions.*;


public class QtumMainNetParams extends AbstractBitcoinNetParams {
    public static final int MAINNET_MAJORITY_WINDOW = 1000;
    public static final int MAINNET_MAJORITY_REJECT_BLOCK_OUTDATED = 950;
    public static final int MAINNET_MAJORITY_ENFORCE_BLOCK_UPGRADE = 750;

    public QtumMainNetParams() {
        super();
        interval = INTERVAL;
        targetTimespan = TARGET_TIMESPAN;
        maxTarget = Utils.decodeCompactBits(0x1d00ffffL);
        dumpedPrivateKeyHeader = 128;
        addressHeader = 80;
        p2shHeader = 50;
        acceptableAddressCodes = new int[] { addressHeader, p2shHeader };
        port = 38100;
        packetMagic = 0xf9beb4d9L;
        bip32HeaderPub = 0x0488B21E; //The 4 byte header that serializes in base58 to "xpub".
        bip32HeaderPriv = 0x0488ADE4; //The 4 byte header that serializes in base58 to "xprv"

        majorityEnforceBlockUpgrade = MAINNET_MAJORITY_ENFORCE_BLOCK_UPGRADE;
        majorityRejectBlockOutdated = MAINNET_MAJORITY_REJECT_BLOCK_OUTDATED;
        majorityWindow = MAINNET_MAJORITY_WINDOW;

        genesisBlock.setDifficultyTarget(0x1d00ffffL);
        genesisBlock.setTime(1575408600L);
        genesisBlock.setNonce(36302);
        id = ID_QTUM_MAINNET;
        subsidyDecreaseBlockCount = 210000;
        spendableCoinbaseDepth = 100;
        String genesisHash = genesisBlock.getHashAsString();
        checkState(genesisHash.equals("00007af309bdd818599502f8fc8af0943c4ce302df2298b14e59abd0c38e07b0"),
                genesisHash);

        // This contains (at a minimum) the blocks which are not BIP30 compliant. BIP30 changed how duplicate
        // transactions are handled. Duplicated transactions could occur in the case where a coinbase had the same
        // extraNonce and the same outputs but appeared at different heights, and greatly complicated re-org handling.
        // Having these here simplifies block connection logic considerably.
        checkpoints.put(0, Sha256Hash.wrap("00007af309bdd818599502f8fc8af0943c4ce302df2298b14e59abd0c38e07b0"));
        checkpoints.put(5000, Sha256Hash.wrap("000042ce1c9aa973256990480f9fc164b22cfa56e69de2e5310f48a23b7d6c8e"));
        checkpoints.put(100000, Sha256Hash.wrap("354bbe24bdfb8b8c7fc2ad1eb92effec87b964aa22c89678d7c606881c705263"));
        checkpoints.put(200000, Sha256Hash.wrap("c3f4826a31bb6bff68d30ff970c6e3b6d26f2996cf5e57ec34d624de6ce2f300"));
        checkpoints.put(250000, Sha256Hash.wrap("e297aaeee42787f9a6514fcb84b27429aebfd5f1ae3a4f57631a9014897cfa8b"));

        dnsSeeds = new String[] {
                "hub1.zh.cash",
                "hub2.zh.cash",
                "hub3.zh.cash",
                "hub4.zh.cash",
                "hub5.zh.cash",
                "hub6.zh.cash",
                "hub7.zh.cashh"
        };
        httpSeeds = new HttpDiscovery.Details[] {
                new HttpDiscovery.Details(
                        ECKey.fromPublicOnly(Utils.HEX.decode("0")),
                        URI.create("http://walletapi.zh.cash/peers")
                )
        };

        addrSeeds = new int[] {

        };
    }

    private static QtumMainNetParams instance;
    public static synchronized QtumMainNetParams get() {
        if (instance == null) {
            instance = new QtumMainNetParams();
        }
        return instance;
    }

    @Override
    public String getPaymentProtocolId() {
        return PAYMENT_PROTOCOL_ID_MAINNET;
    }
}