-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Oct 31, 2023 at 01:06 PM
-- Server version: 10.4.25-MariaDB
-- PHP Version: 8.1.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `atm`
--

-- --------------------------------------------------------

--
-- Table structure for table `transactions`
--

CREATE TABLE `transactions` (
  `transid` int(11) NOT NULL,
  `id` int(11) DEFAULT NULL,
  `amount` int(11) DEFAULT NULL,
  `stat` varchar(10) DEFAULT NULL,
  `bal` int(11) DEFAULT NULL,
  `trans_date` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `transactions`
--

INSERT INTO `transactions` (`transid`, `id`, `amount`, `stat`, `bal`, `trans_date`) VALUES
(1, 2, 2000, 'dep', 2000, '2023-10-30 20:45:55'),
(2, 2, 200, 'wit', 1800, '2023-10-30 20:45:55'),
(3, 2, 200, 'wit', 1600, '2023-10-30 20:45:55'),
(4, 2, 200, 'wit', 1400, '2023-10-30 20:45:55'),
(5, 2, 200, 'wit', 1200, '2023-10-30 20:45:55'),
(6, 2, 100, 'wit', 1100, '2023-10-30 20:45:55'),
(7, 2, 2000, 'dep', 3100, '2023-10-30 20:45:55'),
(8, 2, 200, 'wit', 2900, '2023-10-30 20:45:55'),
(9, 2, 200, 'wit', 2700, '2023-10-30 20:45:55'),
(10, 2, 2000, 'wit', 700, '2023-10-30 20:45:55'),
(11, 3, 5000, 'dep', 5000, '2023-10-30 20:45:55'),
(12, 3, 1000, 'wit', 4000, '2023-10-30 20:45:55'),
(13, 3, 2000, 'wit', 2000, '2023-10-30 20:45:55'),
(14, 2, 200, 'wit', 500, '2023-10-30 20:45:55'),
(15, 2, 200, 'wit', 300, '2023-10-30 20:45:55'),
(16, 2, 300, 'dep', 600, '2023-10-30 20:45:55'),
(17, 2, 200, 'wit', 400, '2023-10-30 20:45:55'),
(18, 2, 200, 'wit', 200, '2023-10-30 20:45:55'),
(19, 2, 8000, 'dep', 8200, '2023-10-30 20:45:55'),
(20, 2, 200, 'trans', 7000, '2023-10-30 20:45:55'),
(21, 2, 200, 'trans', 6800, '2023-10-30 20:45:55'),
(22, 2, 200, 'trans', 6600, '2023-10-30 20:45:55'),
(23, 2, 1, 'trans', 6599, '2023-10-30 20:45:55'),
(24, 2, 100, 'wit', 6499, '2023-10-30 20:45:55'),
(25, 2, 100, 'dep', 6599, '2023-10-30 20:45:55'),
(26, 2, 100, 'dep', 6699, '2023-10-30 20:45:55'),
(27, 2, 1000, 'dep', 7699, '2023-10-30 20:45:55'),
(28, 2, 200, 'trans', 7499, '2023-10-30 20:45:55'),
(29, 3, 300, 'trans', 1700, '2023-10-30 20:45:55'),
(30, 5, 25000, 'dep', 25000, '2023-10-30 21:12:11');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `card` varchar(16) DEFAULT NULL,
  `pin` varchar(4) DEFAULT NULL,
  `uname` varchar(25) DEFAULT NULL,
  `bal` int(11) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `gender` varchar(10) DEFAULT NULL,
  `address` varchar(225) DEFAULT NULL,
  `phone_number` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `card`, `pin`, `uname`, `bal`, `email`, `gender`, `address`, `phone_number`) VALUES
(1, 'admin', '1234', 'admin', 0, NULL, NULL, NULL, NULL),
(2, '3778670123159192', '1874', 'Christus MyNigga', 7499, NULL, NULL, NULL, NULL),
(3, '3996840342686515', '3330', 'Test Tiger', 1700, NULL, NULL, NULL, NULL),
(5, '6319170220058102', '5665', 'Jeffx Malone', 25000, 'sk@gmail.com', 'Male', 'No 11 john\'s street india', '+876542456'),
(6, '6581481124078546', '0643', 'Testing 2', 0, '', '', '', ''),
(7, '4700419879976075', '6778', 'Testing 3', 0, 'test@gmail.com', 'Male', 'No 11 wayanko street Nigeria', '34567876543'),
(8, '1011866783163406', '2717', 'Testing 4', 0, 'test@gmail.com', 'Male', 'No 11 wayanko street Nigeria', '34567887654'),
(9, '6505605387929657', '0195', 'Testing 5', 0, 'test@gmail.com', 'Female', 'No 11 wayanko street Nigeria', '456765432345'),
(10, '0515218269009157', '3388', 'Testing 6', 3000, 'testing@gmail.com', 'Female', 'No 11 FInally completed street', '234543234');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `transactions`
--
ALTER TABLE `transactions`
  ADD PRIMARY KEY (`transid`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `transactions`
--
ALTER TABLE `transactions`
  MODIFY `transid` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=31;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
