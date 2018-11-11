-- phpMyAdmin SQL Dump
-- version 4.8.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Nov 11, 2018 at 08:22 PM
-- Server version: 10.1.33-MariaDB
-- PHP Version: 7.2.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `bank`
--
CREATE DATABASE IF NOT EXISTS `bank` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `bank`;

-- --------------------------------------------------------

--
-- Table structure for table `bank_all_users`
--

DROP TABLE IF EXISTS `bank_all_users`;
CREATE TABLE `bank_all_users` (
  `firstname` varchar(20) NOT NULL,
  `lastname` varchar(20) NOT NULL,
  `account` varchar(12) NOT NULL,
  `password` varchar(20) NOT NULL,
  `age` int(2) NOT NULL,
  `contact` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `bank_all_users`
--

INSERT INTO `bank_all_users` (`firstname`, `lastname`, `account`, `password`, `age`, `contact`) VALUES
('start', '', '011500151000', 'random', 0, ''),
('abhishek', 'sharma', '011500151001', '1234', 14, '15467'),
('riya', 'swami', '011500151002', '1234', 21, '8700654001'),
('simran', '', '011500151003', '1234', 17, '7503232778'),
('simran', 'sharma', '011500151004', '1234', 17, '7503232778');

-- --------------------------------------------------------

--
-- Table structure for table `user1`
--

DROP TABLE IF EXISTS `user1`;
CREATE TABLE `user1` (
  `account` varchar(12) NOT NULL,
  `today_date` varchar(11) NOT NULL,
  `today_time` varchar(11) NOT NULL,
  `trans_type` varchar(6) NOT NULL,
  `amount` int(11) NOT NULL,
  `balance` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user1`
--

INSERT INTO `user1` (`account`, `today_date`, `today_time`, `trans_type`, `amount`, `balance`) VALUES
('011500151003', '2018/09/10', '12:21:59', 'debit', 1000, 1000),
('011500151003', '2018/09/10', '00:39:16', 'debit', 100, 900),
('011500151003', '2018/09/10', '00:39:40', 'debit', 300, 700),
('011500151003', '2018/09/10', '00:42:06', 'debit', 100, 900),
('011500151003', '2018/09/10', '00:49:30', 'debit', 100, 900),
('011500151003', '2018/09/10', '00:54:43', 'debit', 100, 900);

-- --------------------------------------------------------

--
-- Table structure for table `user_login_table`
--

DROP TABLE IF EXISTS `user_login_table`;
CREATE TABLE `user_login_table` (
  `account` varchar(12) NOT NULL,
  `password` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user_login_table`
--

INSERT INTO `user_login_table` (`account`, `password`) VALUES
('011500151001', '1234'),
('011500151002', '1234'),
('011500151003', '1234'),
('011500151004', '1234');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `bank_all_users`
--
ALTER TABLE `bank_all_users`
  ADD PRIMARY KEY (`account`);

--
-- Indexes for table `user_login_table`
--
ALTER TABLE `user_login_table`
  ADD PRIMARY KEY (`account`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
