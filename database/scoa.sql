-- phpMyAdmin SQL Dump
-- version 4.2.11
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: 21-Maio-2015 às 23:09
-- Versão do servidor: 5.6.21
-- PHP Version: 5.6.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `scoa`
--

-- --------------------------------------------------------

--
-- Estrutura da tabela `agendas`
--

CREATE TABLE IF NOT EXISTS `agendas` (
`id` int(11) NOT NULL,
  `description` varchar(255) NOT NULL,
  `id_class` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estrutura da tabela `classes`
--

CREATE TABLE IF NOT EXISTS `classes` (
`id` int(11) NOT NULL,
  `id_professor` varchar(255) NOT NULL,
  `id_room` varchar(255) NOT NULL,
  `id_subject` varchar(255) NOT NULL,
  `semester` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estrutura da tabela `condition_subjects`
--

CREATE TABLE IF NOT EXISTS `condition_subjects` (
  `id_subject` int(11) NOT NULL,
  `id_subject_condition` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estrutura da tabela `courses`
--

CREATE TABLE IF NOT EXISTS `courses` (
`id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `code` varchar(255) NOT NULL,
  `description` varchar(255) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `courses`
--

INSERT INTO `courses` (`id`, `name`, `code`, `description`) VALUES
(1, 'cc', '1', 'Um curso de exatas.');

-- --------------------------------------------------------

--
-- Estrutura da tabela `feedbacks`
--

CREATE TABLE IF NOT EXISTS `feedbacks` (
`id` int(11) NOT NULL,
  `description` varchar(255) NOT NULL,
  `type` enum('suggestion','complaint','','') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estrutura da tabela `presences`
--

CREATE TABLE IF NOT EXISTS `presences` (
`id` int(11) NOT NULL,
  `date` date NOT NULL,
  `id_registration` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estrutura da tabela `professors`
--

CREATE TABLE IF NOT EXISTS `professors` (
`id` int(255) NOT NULL,
  `register` varchar(255) NOT NULL,
  `room` varchar(255) NOT NULL,
  `telephone` varchar(255) NOT NULL,
  `lattes` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `professors`
--

INSERT INTO `professors` (`id`, `register`, `room`, `telephone`, `lattes`, `name`) VALUES
(16, 'ee', 'ee', 'ee', 'ee', 'ee'),
(17, 'dd', 'dd', 'dd', 'dd', 'dd'),
(18, 'a', 'a', 'a', 'a', 'a'),
(20, 'ss', 'ss', 'ss', 'ss', 'ss'),
(21, 'ewfwe', 'wrerwe', 'ewr', 'wrewer', 'ed'),
(22, 'aa', 'aa', 'aa', 'aa', 'aa'),
(23, 'ff', 'ff', 'ff', 'ff', 'ff'),
(24, 'dd', 'dd', 'dd', 'dd', 'dd'),
(25, 'ww', 'ww', 'ww', 'ww', 'ww'),
(26, '1234', '2', '1234', 'lattes', 'Eber');

-- --------------------------------------------------------

--
-- Estrutura da tabela `ratings`
--

CREATE TABLE IF NOT EXISTS `ratings` (
`id` int(11) NOT NULL,
  `grade` varchar(255) NOT NULL,
  `description` varchar(255) NOT NULL,
  `delivery_date` date NOT NULL,
  `id_registration` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estrutura da tabela `registrations`
--

CREATE TABLE IF NOT EXISTS `registrations` (
`id` int(11) NOT NULL,
  `id_student` int(11) NOT NULL,
  `id_class` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estrutura da tabela `rooms`
--

CREATE TABLE IF NOT EXISTS `rooms` (
`id` int(11) NOT NULL,
  `number` varchar(255) NOT NULL,
  `capacity` varchar(255) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `rooms`
--

INSERT INTO `rooms` (`id`, `number`, `capacity`) VALUES
(1, '3', '40');

-- --------------------------------------------------------

--
-- Estrutura da tabela `secretaries`
--

CREATE TABLE IF NOT EXISTS `secretaries` (
`id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `secretaries`
--

INSERT INTO `secretaries` (`id`, `name`) VALUES
(1, 'amanda');

-- --------------------------------------------------------

--
-- Estrutura da tabela `students`
--

CREATE TABLE IF NOT EXISTS `students` (
`id` int(11) NOT NULL,
  `register` varchar(255) NOT NULL,
  `telephone` varchar(255) NOT NULL,
  `address` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `birth_date` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estrutura da tabela `subjects`
--

CREATE TABLE IF NOT EXISTS `subjects` (
`id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `description` varchar(255) NOT NULL,
  `course_id` int(11) NOT NULL,
  `code` varchar(255) NOT NULL,
  `credits` varchar(255) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `subjects`
--

INSERT INTO `subjects` (`id`, `name`, `description`, `course_id`, `code`, `credits`) VALUES
(2, 's', 's', 1, 's', 's'),
(3, 'dddd', 'dddd', 1, 'dddd', 'dddd'),
(4, 'ewqeewqe', 'wqewwqeq', 1, 'eqwewqewq', 'eqweqw');

-- --------------------------------------------------------

--
-- Estrutura da tabela `users`
--

CREATE TABLE IF NOT EXISTS `users` (
`id` int(11) NOT NULL,
  `cpf` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `type` enum('secretary','professor','student','') NOT NULL,
  `type_id` int(11) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `users`
--

INSERT INTO `users` (`id`, `cpf`, `password`, `type`, `type_id`) VALUES
(1, '1234', '1234', 'secretary', 1),
(13, 'ee', 'ee', 'professor', 16),
(14, 'dd', 'dd', 'professor', 17),
(15, 'a', 'a', 'professor', 18),
(17, 'sss', 'ss', 'professor', 20),
(18, 'erwer', 'aa', 'professor', 21),
(19, 'aa', 'bb', 'professor', 22),
(20, 'ff', 'ff', 'professor', 23),
(21, 'dd', 'dd', 'professor', 24),
(22, 'ww', 'ww', 'professor', 25),
(23, '321', 'lalala', 'professor', 26);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `agendas`
--
ALTER TABLE `agendas`
 ADD PRIMARY KEY (`id`);

--
-- Indexes for table `classes`
--
ALTER TABLE `classes`
 ADD PRIMARY KEY (`id`);

--
-- Indexes for table `condition_subjects`
--
ALTER TABLE `condition_subjects`
 ADD PRIMARY KEY (`id_subject`,`id_subject_condition`);

--
-- Indexes for table `courses`
--
ALTER TABLE `courses`
 ADD PRIMARY KEY (`id`);

--
-- Indexes for table `feedbacks`
--
ALTER TABLE `feedbacks`
 ADD PRIMARY KEY (`id`);

--
-- Indexes for table `presences`
--
ALTER TABLE `presences`
 ADD PRIMARY KEY (`id`);

--
-- Indexes for table `professors`
--
ALTER TABLE `professors`
 ADD PRIMARY KEY (`id`);

--
-- Indexes for table `ratings`
--
ALTER TABLE `ratings`
 ADD PRIMARY KEY (`id`);

--
-- Indexes for table `registrations`
--
ALTER TABLE `registrations`
 ADD PRIMARY KEY (`id`);

--
-- Indexes for table `rooms`
--
ALTER TABLE `rooms`
 ADD PRIMARY KEY (`id`);

--
-- Indexes for table `secretaries`
--
ALTER TABLE `secretaries`
 ADD PRIMARY KEY (`id`);

--
-- Indexes for table `students`
--
ALTER TABLE `students`
 ADD PRIMARY KEY (`id`);

--
-- Indexes for table `subjects`
--
ALTER TABLE `subjects`
 ADD PRIMARY KEY (`id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
 ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `agendas`
--
ALTER TABLE `agendas`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `classes`
--
ALTER TABLE `classes`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `courses`
--
ALTER TABLE `courses`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `feedbacks`
--
ALTER TABLE `feedbacks`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `presences`
--
ALTER TABLE `presences`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `professors`
--
ALTER TABLE `professors`
MODIFY `id` int(255) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=27;
--
-- AUTO_INCREMENT for table `ratings`
--
ALTER TABLE `ratings`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `registrations`
--
ALTER TABLE `registrations`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `rooms`
--
ALTER TABLE `rooms`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `secretaries`
--
ALTER TABLE `secretaries`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `students`
--
ALTER TABLE `students`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `subjects`
--
ALTER TABLE `subjects`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=24;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
